package com.listenergao.wanandroid.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.gyf.immersionbar.ktx.immersionBar

/**
 * create on 19/07/29
 * Activity 基类
 * @author ListenerGao
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setStatusBar()
        ButterKnife.bind(this)

    }

    /**
     * 设置状态栏，不满足的情况下，子类可以重写该方法
     */
    private fun setStatusBar() {
        // 设置statusBar字体为深色
        immersionBar {
            statusBarDarkFont(true)
        }
    }

    /**
     * 页面布局，子类设置
     */
    @LayoutRes
    abstract fun getLayoutId(): Int
}