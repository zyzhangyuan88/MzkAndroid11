package com.maizhi.mzk.app.ui.main.service

import com.maizhi.mzk.app.R
import com.maizhi.mzk.app.base.BaseFragment
import com.maizhi.mzk.app.databinding.FragmentHomeBinding
import com.maizhi.mzk.app.ui.main.home.HomeModel

class ServiceFragment : BaseFragment<HomeModel, FragmentHomeBinding>(R.layout.fragment_home){

    companion object{
        fun newInstance() = ServiceFragment()
    }

    override fun initView() {
    }
}