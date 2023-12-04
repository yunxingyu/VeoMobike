package com.veo.common.network.interceptor

import com.veo.common.network.sign.SignFactory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class SignInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return chain.proceed(SignFactory.createSignRequest(request)!!)
    }
}
