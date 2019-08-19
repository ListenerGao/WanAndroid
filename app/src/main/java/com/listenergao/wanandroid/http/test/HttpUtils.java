package com.listenergao.wanandroid.http.test;

import android.util.Log;
import com.listenergao.wanandroid.http.api.Apis;
import com.listenergao.wanandroid.http.response.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create on 19/07/31
 * 网络请求封装java代码，便于后期转kotlin
 * Rxjava2+Retrofit
 *
 * @author listenergao
 */
public class HttpUtils {
    private volatile static HttpUtils sInstance = null;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private static Retrofit mRxJavaRetrofit;


    private HttpUtils() {

    }

    public static HttpUtils getInstance() {
        if (sInstance == null) {
            synchronized (HttpUtils.class) {
                if (sInstance == null) {
                    sInstance = new HttpUtils();
                }
                return sInstance;
            }
        }
        return sInstance;
    }

    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            //日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient
                    .Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();
        }
        return mOkHttpClient;
    }

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Apis.HOST)
                    .client(getOkHttpClient())
                    //配置转化库，采用Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private Retrofit getRxJavaRetrofit() {
        if (mRxJavaRetrofit == null) {
            mRxJavaRetrofit = new Retrofit.Builder()
                    .baseUrl(Apis.HOST)
                    .client(getOkHttpClient())
                    //配置转化库，采用Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRxJavaRetrofit;
    }

    public void testRetrofit() {
//        Apis apis = getRetrofit().create(Apis.class);
//        Call<BaseResponse<BaseDataMode>> homePageArticle = apis.getHomePageArticle("0");
//        homePageArticle.enqueue(new Callback<BaseResponse<BaseDataMode>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<BaseDataMode>> call, Response<BaseResponse<BaseDataMode>> response) {
//                BaseResponse<BaseDataMode> body = response.body();
//                List<BaseDataMode.DataDetails> datas = body.getData().getDatas();
//                if (datas != null) {
//                    Log.d("gys", "size = " + datas.size());
//                }
//                Log.d("gys", "onResponse  " + " code = " + response.code());
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse<BaseDataMode>> call, Throwable t) {
//                Log.d("gys", "onFailure");
//            }
//        });

    }

    /**
     * 获取首页banner
     *
     * @param observer
     */
    public void getHomeBanner(Observer<BaseResponse<List<HomeBanner>>> observer) {
        Apis apis = getRxJavaRetrofit().create(Apis.class);
        Observable<BaseResponse<List<HomeBanner>>> responseObservable = apis.getHomeBanner();
        responseObservable.subscribeOn(Schedulers.io()) //指定网络请求在io线程
                .observeOn(AndroidSchedulers.mainThread()) //指定observer回调在UI主线程中进行
                .subscribe(observer);
    }

    /**
     * 获取首页文章
     *
     * @param currentPage
     * @param observer
     */
    public void getHomeArticle(String currentPage, Observer<BaseResponse<BaseDataMode<List<HomePageArticle>>>> observer) {
        Apis apis = getRxJavaRetrofit().create(Apis.class);
        Observable<BaseResponse<BaseDataMode<List<HomePageArticle>>>> homePageArticle = apis.getHomePageArticle(currentPage);
        homePageArticle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取 知识体系 数据
     * @param observer
     */
    public void getKnowledge(Observer<BaseResponse<List<Knowledge>>> observer) {
        Apis apis = getRxJavaRetrofit().create(Apis.class);
        Observable<BaseResponse<List<Knowledge>>> knowledgeInfo = apis.getKnowledgeInfo();
        knowledgeInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
