package com.maizhi.mzk.app

import com.common.base.BaseApp
import com.hjq.toast.ToastUtils
import com.maizhi.mzk.app.base.AppViewModel

class MyApplication : BaseApp(){

    companion object{
        lateinit var appViewModel: AppViewModel
    }

    override fun onCreate() {
        super.onCreate()
        appViewModel = getAppViewModelProvider().get(AppViewModel::class.java)
        ToastUtils.init(this)
    }


}