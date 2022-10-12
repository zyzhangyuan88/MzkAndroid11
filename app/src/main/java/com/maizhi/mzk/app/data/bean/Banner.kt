package com.maizhi.mzk.app.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 轮播图实体
 *
 * @author LTP  2022/3/22
 */
@Parcelize
data class Banner(
    var imgUrl: String = "",
    var jumpType: String = "",
    var jumpUrl: String = "",
) : Parcelable


