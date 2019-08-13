package com.listenergao.wanandroid.base

import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import com.gyf.immersionbar.ImmersionBar
import com.listenergao.wanandroid.R

/**
 * create on 19/08/06
 * WebView 基类
 * @author ListenerGao
 */
abstract class BaseWebActivity : BaseActivity() {


    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.frame_content)
    lateinit var mFrameContent: FrameLayout


    override fun getLayoutId(): Int {
        return R.layout.activity_base_web
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addContent()

        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .barColor(R.color.colorPrimary).init()

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setIcon(R.drawable.icon_close)

    }

    fun setWebViewTitle(title: String?) {
        supportActionBar?.title = title
    }

    private fun addContent() {
        val webContentLayout = getWebContentLayout()
        if (webContentLayout != -1) {
            val view = layoutInflater.inflate(webContentLayout, null)
            mFrameContent.addView(view)
        }
    }

    @LayoutRes
    abstract fun getWebContentLayout(): Int

}