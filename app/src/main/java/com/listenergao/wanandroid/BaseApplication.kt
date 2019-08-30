package com.listenergao.wanandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex


/**
 * create on 19/07/29
 *
 * @author ListenerGao
 */
class BaseApplication : Application() {

    companion object {
        @get:JvmName("currentApplication") // 设置该方法名称为 currentApplication，否则在java中需要使用BaseApplication.getCurrentApplication()调用
        @JvmStatic
        lateinit var currentApplication: Context
            //设置其 setter 方法为私有的，防止外部调用，只允许外部调用 getter 方法
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
        MultiDex.install(this)

    }

    override fun onCreate() {
        super.onCreate()



    }
}