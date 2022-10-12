package com.maizhi.mzk.app.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppVersion(
    val needUpdate:Boolean = false,
    val updateText:String = "",
    val updateType:Int = 0,
    val latestVersionNumber:String = ""
): Parcelable
