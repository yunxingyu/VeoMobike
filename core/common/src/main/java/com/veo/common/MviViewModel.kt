package com.veo.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent : UiIntent, Action : UiAction, PartialState : UiPartialState, State : UiState> :
    ViewModel() {

    // Create Initial State of View
    private val initialState: State by lazy { createInitialState() }
    protected abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    private val intent = _intent.asSharedFlow()

    private val partialStateChannel = Channel<PartialState>()
    private val actionChannel = Channel<Action>()

    private val reducer: Reducer<State, PartialState> by lazy { createReducer() }
    protected abstract fun createReducer(): Reducer<State, PartialState>

    init {
        observeAction()
        observeReducer()
        subscribeUserIntent()
    }

    private fun observeAction() {
        viewModelScope.launch {
            for (action in actionChannel) {
                println("MVI -> handle action : $action")
                handleAction(action)
            }
        }
    }

    private fun observeReducer() {
        viewModelScope.launch {
            for (partialState in partialStateChannel) {
                println("MVI -> reduce uiState : ${uiState.value} with partialState : $partialState")
                val newState = reducer.reduce(uiState.value, partialState)
                _uiState.value = newState
            }
        }
    }

    /**
     * Subscribe to User Intent trigger by the View
     * Map it into Action and publish it into actionChannel
     */
    private fun subscribeUserIntent() {
        viewModelScope.launch {
            intent.collect {
                println("MVI -> handle User Intent $it")
                val action = handleUserIntent(it)
                actionChannel.send(action)
            }
        }
    }

    /**
     * Should map user Intent into action
     */
    abstract fun handleUserIntent(intent: Intent): Action

    /**
     * This process code and should only trigger :
     * setEvent { Event() }
     * or
     * setPartialState { PartialState() }
     */
    protected abstract suspend fun handleAction(action: Action)

    protected fun setPartialState(builder: () -> PartialState) {
        val newPartialState = builder()
        viewModelScope.launch { partialStateChannel.send(newPartialState) }
    }

    protected fun setAction(builder: () -> Action) {
        val newAction = builder()
        viewModelScope.launch { actionChannel.send(newAction) }
    }

    /**
     * Dispatch Intent
     */
    fun dispatchIntent(intent: Intent) {
        val newIntent = intent
        viewModelScope.launch { _intent.emit(newIntent) }
    }
}

abstract class Reducer<UiState, UiPartialState> {
    abstract fun reduce(currentState: UiState, partialState: UiPartialState): UiState
}

// User Action
interface UiIntent

// Action
interface UiAction

// PartialState of View ? should be here ?
interface UiPartialState

// State of View
interface UiState {
    val event: UiEvent?
}

// Effect which we want to show only one
interface UiEvent