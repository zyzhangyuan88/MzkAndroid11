package com.maizhi.mzk.app.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandListResult(
    var desc: String = "",
    var brands : ArrayList<BrandInfo>

):Parcelable
