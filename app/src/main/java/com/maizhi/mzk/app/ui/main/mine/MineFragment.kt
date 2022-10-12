package com.maizhi.mzk.app.ui.main.mine

import com.common.base.utils.LogUtil
import com.common.base.utils.ToastUtil
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.maizhi.mzk.app.R
import com.maizhi.mzk.app.base.BaseFragment
import com.maizhi.mzk.app.databinding.FragmentMineBinding
import com.maizhi.mzk.app.ui.login.AccountLoginActivity

class MineFragment : BaseFragment<MineViewModule, FragmentMineBinding>(R.layout.fragment_mine){

    companion object{
        fun newInstance() = MineFragment()
    }

    override fun initView() {

        mBinding.apply {
            btnUpload.setOnClickListener {
                uploadFile()
            }

            btnLogin.setOnClickListener {

                AccountLoginActivity.launch(requireContext())
            }
        }
    }

    override fun createObserve() {
        super.createObserve()
        mViewModel.fileResp.observe(viewLifecycleOwner){
            LogUtil.d("====uploadFile====>:$it")
            if (it != null) {
                ToastUtil.showShort(context,it)
            }
        }
    }

    private fun uploadFile(){
        XXPermissions.with(this).permission(Permission.MANAGE_EXTERNAL_STORAGE)
            .request { _, all ->
                if(all){
                    val filePath = "/mnt/sdcard/test11.png"
                    mViewModel.upLoadFile(filePath)
                }
            }
    }
}