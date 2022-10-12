package com.maizhi.mzk.app.base

import androidx.databinding.ViewDataBinding
import com.common.base.base.BaseVMBActivity
import com.common.base.base.BaseViewModel


/**
 * 在这里只做了统一处理跳转登录页面的逻辑
 * @author Alex
 */
abstract class BaseActivity<VM:BaseViewModel,B:ViewDataBinding>(contentViewResId: Int) :
    BaseVMBActivity<VM,B>(contentViewResId){

    override fun createObserve() {
        super.createObserve()
        mViewModel.errorResponse.observe(this){
            if (it?.code == -1001) {
                // 需要登录，这里主要是针对收藏操作，不想每个地方都判断一下
                // 当然你也可以封装一个收藏的组件，在里面统一判断跳转
//                LoginActivity.launch(this)
            }
        }
    }

}