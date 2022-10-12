package com.maizhi.mzk.app.ui.main.home

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.common.base.ext.load
import com.hjq.toast.ToastUtils
import com.maizhi.mzk.app.data.bean.Banner
import com.youth.banner.adapter.BannerAdapter

/**
 * 首页banner的adapter
 *
 */
class MyBannerAdapter(dataList:ArrayList<Banner>) : BannerAdapter<Banner,MyBannerAdapter.BannerViewHolder>(dataList){


    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)

        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, data: Banner, position: Int, size: Int) {
        holder.imageView.apply {
            load(data.imgUrl)
            setOnClickListener {
                ToastUtils.show(data.jumpType)
            }
        }

    }

    inner class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

}