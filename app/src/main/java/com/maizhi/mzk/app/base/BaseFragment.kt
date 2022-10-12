package com.maizhi.mzk.app.base

import androidx.databinding.ViewDataBinding
import com.common.base.base.BaseVMBFragment
import com.common.base.base.BaseViewModel

/**
 * 在这里只做了统一处理跳转登录页面的逻辑
 * @author Alex
 */
abstract class BaseFragment<VM:BaseViewModel,B:ViewDataBinding> (contentViewResId: Int) :
    BaseVMBFragment<VM, B>(contentViewResId){

    override fun createObserve() {
        super.createObserve()
        mViewModel.errorResponse.observe(viewLifecycleOwner){
            if (it?.code == -1001) { // 需要登录
//                LoginActivity.launch(requireContext())
            }
        }
    }
}