package com.listenergao.wanandroid.http

import com.listenergao.wanandroid.http.api.Apis
import com.listenergao.wanandroid.http.response.BaseResponse
import com.listenergao.wanandroid.http.response.Knowledge
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * create on 19/07/30
 * 网络请求辅助类
 *
 * 通过 object 实现的单例是一个饿汉式的单例，并且实现了线程安全。
 *
 * 使用 object 修饰的对象中的变量和函数都是静态的，
 *
 * @author ListenerGao
 */
object HttpHelper {

    private var mOkHttpClient: OkHttpClient
    private var mRetrofit: Retrofit

    init {
        //日志拦截器
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //初始化OkhttpClient
        mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()


        //初始化Retrofit
        mRetrofit = Retrofit.Builder()
            .baseUrl(Apis.HOST)
            .client(mOkHttpClient)
            //配置转化库，采用Gson
            .addConverterFactory(GsonConverterFactory.create())
            //配置回调库，采用RxJava
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * 获取 知识体系 数据
     * @param observer
     */
    fun getKnowledge(observer: Observer<BaseResponse<List<Knowledge>>>) {
        val apis = mRetrofit.create(Apis::class.java)
        val knowledgeInfo = apis.getKnowledgeInfo()
        knowledgeInfo.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }


}