package com.maizhi.mzk.app.ui.main.monitor

import com.maizhi.mzk.app.R
import com.maizhi.mzk.app.base.BaseFragment
import com.maizhi.mzk.app.databinding.FragmentHomeBinding
import com.maizhi.mzk.app.ui.main.home.HomeModel


class MonitorFragment : BaseFragment<HomeModel, FragmentHomeBinding>(R.layout.fragment_home){

    companion object{
        fun newInstance() = MonitorFragment()
    }

    override fun initView() {
    }
}