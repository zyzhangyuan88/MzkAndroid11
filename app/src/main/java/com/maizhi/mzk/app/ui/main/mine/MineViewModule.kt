package com.maizhi.mzk.app.ui.main.mine

import com.common.base.base.BaseViewModel
import com.common.base.ext.handleRequest
import com.common.base.ext.launch
import com.common.base.utils.SingleLiveEvent
import com.maizhi.mzk.app.data.DataRepository
import com.maizhi.mzk.app.data.DataRepository.fileToRequestBody

class MineViewModule : BaseViewModel() {

    val fileResp = SingleLiveEvent<String?>()

    override fun start() {

    }

    fun upLoadFile(filePath: String) {
        launch({
            val fileToRequestBody = fileToRequestBody(filePath, "101")
            handleRequest(DataRepository.uploadFile(fileToRequestBody), {
                fileResp.value = it.data
            })
        })
    }


}