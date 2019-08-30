package com.listenergao.wanandroid.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.base.BaseActivity

class TestActivity : BaseActivity() {

    lateinit var view: View


    override fun getLayoutId(): Int {
        return R.layout.test_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = findViewById(R.id.tv_title)

        test(view)
    }

    private fun test(view: View?) {
        Log.d("gys", "view id = ${view?.id}")


    }

}