package com.maizhi.mzk.app.ui.main

import android.provider.MediaStore.getVersion
import androidx.lifecycle.MutableLiveData
import com.common.base.base.BaseViewModel
import com.common.base.ext.handleRequest
import com.common.base.ext.launch
import com.maizhi.mzk.app.data.DataRepository
import com.maizhi.mzk.app.data.bean.AppVersion

class MainViewModel : BaseViewModel() {

    companion object {
        /** 每页显示的条目大小 */
        const val PAGE_SIZE = 20
    }

    val appVersionLiveData = MutableLiveData<AppVersion>()

    override fun start() {

    }

    fun fetchAppVersion(json: String) {
        launch({
            handleRequest(DataRepository.getAppVersion(DataRepository.getRequestBody(json)), {
                appVersionLiveData.value = it.data
            })
        })
    }

}