package com.listenergao.wanandroid.http.api

import com.listenergao.wanandroid.http.response.BaseDataMode
import com.listenergao.wanandroid.http.response.BaseResponse
import com.listenergao.wanandroid.http.response.HomeBanner
import com.listenergao.wanandroid.http.response.HomePageArticle
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList

/**
 * create on 19/07/30
 * 所有请求的url
 * @author ListenerGao
 */
interface Apis {
    companion object {
        const val HOST = "https://www.wanandroid.com/"
    }

    /**
     * 首页文章列表
     *
     * https://www.wanandroid.com/article/list/0/json
     */
    @GET("article/list/{pagNum}/json")
    fun getHomePageArticle(@Path("pagNum") pagNum: String): Observable<BaseResponse<BaseDataMode<ArrayList<HomePageArticle>>>>

    /**
     * 首页banner
     *
     * https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getHomeBanner(): Observable<BaseResponse<List<HomeBanner>>>

}