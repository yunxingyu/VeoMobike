package com.veo.mobikemap.repository

import android.app.Application
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MobikeRepository @Inject constructor(application: Application, mRetrofit: Retrofit)