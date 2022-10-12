package com.maizhi.mzk.app.ui.main.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.hjq.toast.ToastUtils
import com.maizhi.mzk.app.R
import com.maizhi.mzk.app.data.bean.BrandInfo
import com.maizhi.mzk.app.databinding.HomeItemBrandBinding

class BrandAdapter :
    BaseQuickAdapter<BrandInfo,BaseDataBindingHolder<HomeItemBrandBinding>>(R.layout.home_item_brand),
        LoadMoreModule{

    override fun convert(holder: BaseDataBindingHolder<HomeItemBrandBinding>, item: BrandInfo) {
        holder.dataBinding?.apply {
            brand = item
            executePendingBindings()

            clItem.setOnClickListener {
                ToastUtils.show("详情")
            }
        }
    }

}