package com.listenergao.wanandroid.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.http.response.HomePageArticle

class HomeArticleAdapter(data: List<HomePageArticle>) :
    BaseQuickAdapter<HomePageArticle, BaseViewHolder>(R.layout.item_home_article, data) {


    override fun convert(helper: BaseViewHolder, item: HomePageArticle) {
        helper.setText(R.id.tv_author, item.author)
            .setText(R.id.tv_title, item.title)
            .setText(R.id.tv_category, item.chapterName)
            .setText(R.id.tv_nice_data, item.niceDate)
            .addOnClickListener(R.id.iv_collection_state)

    }

}