package com.common.base.http.interceptor

import com.common.base.data.local.UserManager
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * 头部拦截器
 * Created by Alex on 2022-12-23.
 */
class HeaderInterceptorV2 : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()
        UserManager.getToken()?.let { builder.addHeader("MZ_MZK_TOKEN", it) }
        builder.addHeader("requestId", UUID.randomUUID().toString())
        builder.addHeader("request_src", "mzk_app")
        builder.addHeader("v","2")
        builder.addHeader("Content-type", "application/json; charset=utf-8")

        return chain.proceed(builder.build())
    }
}