package com.listenergao.wanandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.base.BaseFragment

/**
 * create on 19/07/29
 * 项目页
 * @author ListenerGao
 */
class ProjectFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.project_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }
}