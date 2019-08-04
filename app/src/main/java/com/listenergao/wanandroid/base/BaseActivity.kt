package com.listenergao.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar

/**
 * create on 19/07/29
 * Activity 基类
 * @author ListenerGao
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置statusBar字体为深色
        immersionBar {
            statusBarDarkFont(true)
        }
    }
}