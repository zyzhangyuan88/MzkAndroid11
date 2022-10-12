package com.common.base.http.interceptor

import com.common.base.BuildConfig
import com.common.base.utils.LogUtil
import okhttp3.logging.HttpLoggingInterceptor

/**
 * okhttp 日志拦截器
 * @author Alex  2022/3/21
 */
val logInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        // 使用自己的日志工具接管
        LogUtil.d(message)
    }
}).setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC)