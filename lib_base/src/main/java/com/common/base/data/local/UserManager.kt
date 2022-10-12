package com.common.base.data.local

import com.tencent.mmkv.MMKV

object UserManager {

    /** MMKV独有的mmapId */
    private const val MMKV_MAP_ID = "user"

    /** 保存登录成功的用户的Json串的KEY */
    private const val KEY_USER = "user_data"

    /** 保存登录成功的用户Token */
    private const val KEY_TOKEN = "token"

    private val mmkv by lazy { MMKV.mmkvWithID(MMKV_MAP_ID) }

    /**
     * 存储用户到本地
     */
    fun saveUser(user: User){
        mmkv.encode(KEY_USER,user)
    }


    /**
     * 获取存储的本地用户信息
     *
     * @return 本地用户信息
     */
    fun getUser(): User? {
        return mmkv.decodeParcelable(KEY_USER, User::class.java)
    }

    /**
     * 是否已登录(自动登录的判断)
     *
     * @return 是否已登录
     */
    fun isLogin(): Boolean {
        return getUser() != null
    }

    /**
     * 存储用户token到本地
     *
     * @param token    Token
     */
    fun saveToken(token: String?) {
        mmkv.encode(KEY_TOKEN, token)
    }

    /**
     * 获取登录用户Token信息
     *
     * @return 登录Token
     */
    fun getToken(): String? {
        return mmkv.decodeString(KEY_TOKEN)
    }

    /**
     * 退出登录（清空所有用户数据）
     */
    fun logout() {
        // 清除登录信息
        mmkv.remove(KEY_USER)
        mmkv.remove(KEY_TOKEN)
    }

}