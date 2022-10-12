package com.common.base.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 用户信息
 * @author Alex
 */

@Parcelize
data class User(
    val id: String = "",
    val nickname: String = "",
    val password: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = ""
) : Parcelable