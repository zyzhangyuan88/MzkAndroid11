package com.common.base.http

import com.common.base.BaseApp.Companion.appContext
import com.common.base.BaseConstants
import com.common.base.http.interceptor.CacheInterceptor
import com.common.base.http.interceptor.HeaderInterceptor
import com.common.base.http.interceptor.HeaderInterceptorV2
import com.common.base.http.interceptor.logInterceptor
import com.common.base.utils.LogUtil
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Retrofit管理类
 *
 * @author Alex  2022/3/21
 */
object RetrofitManager {

    /** 请求超时时间 */
    private const val TIME_OUT_SECONDS = 10

    /** 请求cookie */
    private val cookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(appContext)
        )
    }



    /** OkHttpClient相关配置 */
    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            // 请求过滤器
            .addInterceptor(logInterceptor)
            //添加头部
            .addInterceptor(HeaderInterceptor())
            //设置缓存配置,缓存最大10M,设置了缓存之后可缓存请求的数据到data/data/包名/cache/net_cache目录中
            .cache(Cache(File(appContext.cacheDir, "net_cache"), 10 * 1024 * 1024))
            //添加缓存拦截器 可传入缓存天数
            .addInterceptor(CacheInterceptor(30))
            // 请求超时时间
            .connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .retryOnConnectionFailure(true)
            .build()

    /** OkHttpClient相关配置 */
    private val clientV2: OkHttpClient
        get() = OkHttpClient.Builder()
            // 请求过滤器
            .addInterceptor(logInterceptor)
            //添加头部
            .addInterceptor(HeaderInterceptorV2())
            //设置缓存配置,缓存最大10M,设置了缓存之后可缓存请求的数据到data/data/包名/cache/net_cache目录中
            .cache(Cache(File(appContext.cacheDir, "net_cache"), 10 * 1024 * 1024))
            //添加缓存拦截器 可传入缓存天数
            .addInterceptor(CacheInterceptor(30))
            // 请求超时时间
            .connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .retryOnConnectionFailure(true)
            .build()

    /**
     * Retrofit相关配置
     */
    fun <T> getService(serviceClass: Class<T>, baseUrl: String? = null): T {
        LogUtil.d(baseUrl ?: BaseConstants.BASE_URL)
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl ?: BaseConstants.BASE_URL)
            .build()
            .create(serviceClass)
    }

    /**
     * Retrofit相关配置HeadV2
     */
    fun <T> getServiceHeadV2(serviceClass: Class<T>, baseUrl: String? = null): T {
        LogUtil.d(baseUrl ?: BaseConstants.BASE_URL)
        return Retrofit.Builder()
            .client(clientV2)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl ?: BaseConstants.BASE_URL)
            .build()
            .create(serviceClass)
    }
}