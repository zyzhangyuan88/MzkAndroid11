package com.maizhi.mzk.app.data

import com.common.base.BaseConstants
import com.common.base.data.bean.ApiResponse
import com.common.base.http.BaseRepository
import com.common.base.http.RetrofitManager
import com.maizhi.mzk.app.data.api.Api
import com.maizhi.mzk.app.data.bean.AppVersion
import com.maizhi.mzk.app.data.bean.Banner
import com.maizhi.mzk.app.data.bean.BrandListResult
import com.maizhi.mzk.app.data.bean.LoginUserResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

object DataRepository:BaseRepository(),Api{

    private val service by lazy { RetrofitManager.getService(Api::class.java) }

    private val serviceV2 by lazy { RetrofitManager.getServiceHeadV2(Api::class.java) }

    private val crmService by lazy { RetrofitManager.getService(Api::class.java,BaseConstants.HZ_CRM_URL) }

    private val mallService by lazy { RetrofitManager.getService(Api::class.java,BaseConstants.MALL_URL) }

    override suspend fun getAppVersion(requestBody: RequestBody): ApiResponse<AppVersion> {
       return apiCall {
           crmService.getAppVersion(requestBody)
       }
    }

    override suspend fun uploadFile(partMap: MutableMap<String, RequestBody>): ApiResponse<String> {
        return apiCall {
            service.uploadFile(partMap)
        }
    }

    override suspend fun accountLogin(requestBody: RequestBody): ApiResponse<LoginUserResponse> {
        return apiCall {
            service.accountLogin(requestBody)
        }
    }


    override suspend fun getBanner(params:Map<String, String>): ApiResponse<List<Banner>> {
        return apiCall { crmService.getBanner(params) }
    }


    override suspend fun getMallPageList(params: Map<String, String>): ApiResponse<BrandListResult> {
        return apiCall {
            mallService.getMallPageList(params)
        }
    }

    /**
     *
     */
    fun getRequestBodyText(json: String): RequestBody {
        return  json.toRequestBody("text/plain;charset=utf-8".toMediaType())
    }


    fun getRequestBody(json: String): RequestBody{
        return json.toRequestBody("application/json".toMediaType())
    }

    /**
     * code 上传文件来源: 101 APP
     * filePath 文件路径
     */
    fun fileToRequestBody(filePath: String, code: String): MutableMap<String, RequestBody> {

        val requestBodyMap: MutableMap<String, RequestBody> = HashMap()
        val file = File(filePath)
        val requestBody = file.asRequestBody("file/*".toMediaType())
        try {
            requestBodyMap["file\"; filename=\"" + URLEncoder.encode(file.name, "utf-8")] = requestBody
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        val codes  = code.toRequestBody("text/plain;charset=utf-8".toMediaType());
        requestBodyMap["code"] = codes

        return requestBodyMap
    }
}