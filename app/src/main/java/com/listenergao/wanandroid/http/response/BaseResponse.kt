package com.listenergao.wanandroid.http.response

/**
 * create on 19/07/30
 * 网络请求基类response
 * @author ListenerGao
 */
class BaseResponse<T> {
    /**
     * 0：成功，1：失败
     */
    public var errorCode: Int = -1
    public var errorMsg: String? = null
    public var data: T? = null

    companion object {
        val SUCCESS = 0
        val FAIL = 1
    }

    /**
     * 判断返回的数据是否成功
     */
    fun isSuccess(): Boolean {
        if (errorCode == 0) {
            return true
        }
        return false
    }

    /**
     * 是否有错误信息
     */
    fun hasErrorMsg(): Boolean {
        //errorMsg 不为空，并且长度大于0
        if (errorMsg?.length ?: 0 > 0) {
            return true
        }
        return false
    }


}