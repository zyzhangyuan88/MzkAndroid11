package com.common.base.http

import com.common.base.data.bean.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * Repository数据仓库基类，主要用于协程的调用
 *
 * @author Alex  2022/3/23
 */
open class BaseRepository {

    suspend fun <T> apiCall(api: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return withContext(Dispatchers.IO) { api.invoke() }
    }



}