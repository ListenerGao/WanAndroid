package com.listenergao.wanandroid.http.response

/**
 * create on 19/07/31
 * 网络请求，带有翻页数据的基类
 */
class BaseDataMode<T> {

    var curPage: Int = 0
    var offset: Int = 0
    var isOver: Boolean = false
    var pageCount: Int = 0
    var size: Int = 0
    var total: Int = 0
    var datas: T? = null

}
