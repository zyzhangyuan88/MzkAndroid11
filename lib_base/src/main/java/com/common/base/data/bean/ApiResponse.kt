package com.common.base.data.bean

/**
 * 接口返回外层封装实体
 *
 */
data class ApiResponse<T>(
    val data: T,
    val code: Int,
    val success:Boolean,
    val msg: String
)