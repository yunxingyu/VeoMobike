package com.veo.common.network.sign

import okhttp3.Request

/**
 * 签名工厂
 */
object SignFactory {
    /**
     * 入口
     * 调用不同的加密类
     *
     * @param request 原请求
     * @return 处理后的请求
     */
    fun createSignRequest(request: Request): Request? {
        /* 添加公共头 */
        val newRequest = request.newBuilder()
//            .addHeader(
//                "OCC-USER-AGENT",
//                (("APPID:" + UserInfoUtil.getAppId()).toString() +
//                        " APPVersion:" + UserInfoUtil.getAppVersion()).toString() +
//                        " AndroidVersion:" + Build.VERSION.RELEASE
//            )
            .build()
        val httpUrl = request.url
        if (isNeedSign(httpUrl.host)) {
        }
        return newRequest
    }

    /**
     * 是否需要加密
     *
     * @param host 请求的host地址
     * @return boolean
     */
    private fun isNeedSign(host: String): Boolean {
        return true
//        (Api.BASE_BOOKSTORE_URL_NEW.contains(host) ||
//                Api.BASE_URL_NEW.contains(host) ||
//                Api.LOGIN_REGISTER_URL.contains(host) ||
//                Api.BASE_URL_OCCTIKU.contains(host) ||
//                Api.BASE_URL_OCC.contains(host) ||
//                Api.LIVE_URL.contains(host))
    }
}
