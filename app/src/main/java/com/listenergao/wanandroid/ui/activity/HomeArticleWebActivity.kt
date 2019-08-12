package com.listenergao.wanandroid.ui.activity

import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.base.BaseWebActivity

/**
 * create on 19/08/08
 * 文章展示 WebView
 * @author ListenerGao
 */
class HomeArticleWebActivity : BaseWebActivity() {

    companion object {
        const val HOME_ARTICLE_URL = "homeArticleUrl"
        const val HOME_ARTICLE_TITLE = "homeArticleTitle"
    }

    lateinit var mWebView: WebView

    override fun getWebContentLayout(): Int {
        return R.layout.web_view_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeArticleUrl = intent.getStringExtra(HOME_ARTICLE_URL)
        val title = intent.getStringExtra(HOME_ARTICLE_TITLE)
        setWebViewTitle(title)
        initWebView(homeArticleUrl)

    }

    private val mWebViewClient = object : WebViewClient() {
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {

        }

//        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//            Log.d("gys","error = ${request?.requestHeaders}")
//            return true
//        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            Log.d("gys", "error = $description  errorCode = $errorCode   failingUrl = $failingUrl")
        }

    }

    private val mWebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            if (title != null) {
//                setWebViewTitle(title)
            }
        }
    }


    private fun initWebView(url: String?) {
        Log.d("gys", "url = $url")
        mWebView = findViewById(R.id.web_view)
        mWebView.webChromeClient = mWebChromeClient
        mWebView.webViewClient = mWebViewClient
        mWebView.loadUrl(url)

    }


}