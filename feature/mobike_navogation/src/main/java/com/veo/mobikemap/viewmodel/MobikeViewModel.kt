package com.veo.mobikemap.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.veo.common.network.DispatcherProvider
import com.veo.mobikemap.repository.MobikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MobikeViewModel@Inject constructor(
    application: Application,
    private val mobikeRepository: MobikeRepository,
//    private val trashNote: TrashNoteRepo,
    private val dispatcherProvider: DispatcherProvider,
//    private val alarmScheduler: AlarmScheduler,
) : AndroidViewModel(application)
