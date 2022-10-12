package com.maizhi.mzk.app.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import com.common.base.ext.hideLoading
import com.common.base.ext.showLoading
import com.common.base.utils.ToastUtil
import com.hjq.toast.ToastUtils
import com.maizhi.mzk.app.R
import com.maizhi.mzk.app.base.AppViewModel
import com.maizhi.mzk.app.base.BaseActivity
import com.maizhi.mzk.app.databinding.ActivityLoginAccountBinding
import org.json.JSONObject

class AccountLoginActivity :
    BaseActivity<AccountLoginViewModule, ActivityLoginAccountBinding>(R.layout.activity_login_account) {

    companion object {

        fun launch(context: Context) {
            context.startActivity(Intent(context, AccountLoginActivity::class.java))
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.apply {

            passwordEt.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    line.setBackgroundColor(resources.getColor(R.color.app_color_335CF3))
                } else {
                    line.setBackgroundColor(resources.getColor(R.color.app_color_EEEEEE))
                }
            }

            accountLoginTv.setOnClickListener {

                login()
            }

            tips1Tv.setOnClickListener {

            }

            tips2Tv.setOnClickListener {

            }

            otherLoginTv.setOnClickListener {

            }

            codeLogin.setOnClickListener {

            }

            agreeIv.setOnClickListener {
                agreeIv.isSelected = !agreeIv.isSelected
            }

            visibilityLayout.setOnClickListener {
                if (visibilityIv.isSelected) {
                    visibilityIv.isSelected = false
                    passwordEt.transformationMethod = PasswordTransformationMethod()
                } else {
                    visibilityIv.isSelected = true
                    passwordEt.transformationMethod = HideReturnsTransformationMethod()
                }
                passwordEt.setSelection(passwordEt.text.length)
            }

        }
    }

    private fun login() {

        val phone = mBinding.phoneEt.text.trim().toString()
        val password = mBinding.passwordEt.text.trim().toString()
        val isCheck = mBinding.agreeIv.isSelected
        Log.d("====", "===1====>:$phone")
        Log.d("====", "===1====>:$password")
        Log.d("====", "===1====>:$isCheck")

        when {
            TextUtils.isEmpty(phone) -> ToastUtils.show("账号不能为空")
            TextUtils.isEmpty(password) -> ToastUtils.show("密码不能为空")
            !isCheck -> ToastUtils.show("请先阅读并同意我们的用户协议和隐私政策")
            else -> {
                showLoading("登录中...")
                Log.d("====", "===2====>:$phone")
                Log.d("====", "===2====>:$password")
                Log.d("====", "===2====>:$isCheck")
                val map = HashMap<String, Any>()
                map["phone"] = phone
                map["password"] = password
                mViewModel.accountLogin(JSONObject(map as Map<*, *>).toString(), {
                    hideLoading()
                    onBackPressed()
                }, {
                    hideLoading()
                })
            }
        }


    }

}