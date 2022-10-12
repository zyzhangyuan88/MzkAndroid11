package com.maizhi.mzk.app.data.bean

import android.os.Parcelable
import com.common.base.data.local.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUserResponse(
    val token: String? = "",
    val user: User?
) : Parcelable