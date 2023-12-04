package com.veo.usercenter.repository

import android.app.Application
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MineRepository @Inject constructor(application: Application, mRetrofit: Retrofit)
