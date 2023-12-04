package com.veo.usercenter.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.veo.common.network.DispatcherProvider
import com.veo.usercenter.repository.MineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MineViewModel@Inject constructor(
    application: Application,
    private val mineRepository: MineRepository,
//    private val trashNote: TrashNoteRepo,
    private val dispatcherProvider: DispatcherProvider,
//    private val alarmScheduler: AlarmScheduler,
) : AndroidViewModel(application)
