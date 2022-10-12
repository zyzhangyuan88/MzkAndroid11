package com.maizhi.mzk.app.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.common.base.base.BaseViewModel
import com.common.base.ext.handleRequest
import com.common.base.ext.launch
import com.maizhi.mzk.app.AppConstants
import com.maizhi.mzk.app.data.DataRepository
import com.maizhi.mzk.app.data.bean.Banner
import com.maizhi.mzk.app.data.bean.BrandInfo

class HomeModel:BaseViewModel() {

    /** Banner列表 */
    val bannerListLiveData = MutableLiveData<List<Banner>>()

    /** 商标列表 */
    val brandListLiveData = MutableLiveData<List<BrandInfo>>()

    override fun start() {
        fetchBanners()
        fetchBrandPageList()
    }

    fun fetchBanners(){
        launch({
            val map = mapOf<String,String>("appCode" to "2")
            handleRequest(DataRepository.getBanner(map), {
                bannerListLiveData.value = it.data

            })
        })
    }


    /**
     * 请求文章列表，第一页包括一个请求置顶的接口和一个文章分页列表的接口
     *
     * @param pageNo 页码（0表示请求第1页）
     */
    fun fetchBrandPageList(pageNo: Int = 1) {
        launch({
            val map = HashMap<String, String>()
            map["Action"] = "brandList"
            map["page"] = pageNo.toString()
            map["page_size"] = AppConstants.PAGE_SIZE.toString()
            map["AccessKeyId"] = AppConstants.ACCESS_KEY_ID
            map["AccessKeySecret"] = AppConstants.ACCESS_KEY_SECRET

            handleRequest(
                DataRepository.getMallPageList(map),
                { brandListLiveData.value = it.data.brands})
        })
    }

}