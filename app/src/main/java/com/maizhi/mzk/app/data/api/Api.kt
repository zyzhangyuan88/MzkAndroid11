package com.maizhi.mzk.app.data.api

import com.common.base.data.bean.ApiResponse
import com.maizhi.mzk.app.data.bean.AppVersion
import com.maizhi.mzk.app.data.bean.Banner
import com.maizhi.mzk.app.data.bean.BrandListResult
import com.maizhi.mzk.app.data.bean.LoginUserResponse
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.http.*

interface Api {

    @POST("app/update/config")
    suspend fun getAppVersion(
        @Body requestBody: RequestBody
    ): ApiResponse<AppVersion>


    @Multipart
    @POST("common/uploadFile")
    suspend fun uploadFile(
        @PartMap partMap: MutableMap<String, RequestBody>
    ): ApiResponse<String> //单文件通用上传


    @POST("mzkquery/appMy/login")
    suspend fun accountLogin(
        @Body requestBody: RequestBody
    ): ApiResponse<LoginUserResponse>


    /** 获取首页banner数据 */
    @GET("app/topBanner/list")
    suspend fun getBanner(@QueryMap params:Map<String, String>): ApiResponse<List<Banner>>

    @POST("hz_api")
    @FormUrlEncoded
    suspend fun getMallPageList(@FieldMap params: Map<String, String>): ApiResponse<BrandListResult>
}