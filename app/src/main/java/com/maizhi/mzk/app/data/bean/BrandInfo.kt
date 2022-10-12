package com.maizhi.mzk.app.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandInfo(
    var reg_no: String = "",
    var name: String = "",
    var logo_path: String = "",
    var category_name: String = "",
    var price: String = "",
    var category_id: String = ""
):Parcelable
