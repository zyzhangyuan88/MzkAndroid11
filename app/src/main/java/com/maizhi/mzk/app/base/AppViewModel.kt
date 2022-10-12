package com.maizhi.mzk.app.base

import androidx.lifecycle.MutableLiveData
import com.common.base.base.BaseViewModel
import com.common.base.data.local.User

class AppViewModel:BaseViewModel() {
    override fun start() {}

    /** 全局用户 */
    val userEvent = MutableLiveData<User?>()

}