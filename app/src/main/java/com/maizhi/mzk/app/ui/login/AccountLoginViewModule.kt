package com.maizhi.mzk.app.ui.login

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.common.base.base.BaseViewModel
import com.common.base.ext.handleRequest
import com.common.base.ext.hideLoading
import com.common.base.ext.launch
import com.hjq.toast.ToastUtils
import com.maizhi.mzk.app.MyApplication
import com.maizhi.mzk.app.data.DataRepository
import com.common.base.data.local.UserManager

class AccountLoginViewModule : BaseViewModel() {

    val userName = ObservableField("")
    val userPwd = ObservableField("")

    /** 登录按键是否可点击(这样可避免在dataBinding中写较复杂的逻辑) */
    val loginBtnEnable = object : ObservableBoolean(userName, userPwd) {
        override fun get(): Boolean {
            return !userName.get()?.trim().isNullOrEmpty() && !userPwd.get().isNullOrEmpty()
        }
    }

    override fun start() {
    }

    fun accountLogin(json: String,successCall: () -> Any? = {},errorBlock: () -> Any? = {} ) {
        launch({
            handleRequest(DataRepository.accountLogin(DataRepository.getRequestBody(json)),successBlock = {
                if(TextUtils.isEmpty(it.data.token)){
                    ToastUtils.show(it.msg)
                    hideLoading()
                }else{
                    it.data.user?.let { it1 -> UserManager.saveUser(it1) }
                    UserManager.saveToken(it.data.token)
                    MyApplication.appViewModel.userEvent.value = it.data.user
                    successCall.invoke()
                }
            },errorBlock = {
                false
            })
        })
    }


}