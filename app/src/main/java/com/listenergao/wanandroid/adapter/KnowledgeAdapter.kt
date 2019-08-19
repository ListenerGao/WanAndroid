package com.listenergao.wanandroid.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.http.response.Knowledge

/**
 * create on 19/08/19
 * 知识体系Adapter
 * @author ListenerGao
 */
class KnowledgeAdapter(data: List<Knowledge>) :
    BaseQuickAdapter<Knowledge, BaseViewHolder>(R.layout.item_knowledge, data) {


    override fun convert(helper: BaseViewHolder, item: Knowledge) {

        val children = item.children
        val sb = StringBuffer()
        children?.forEach {
            sb.append(it.name).append(", ")
        }

        helper.setText(R.id.tv_title, item.name)
            .setText(R.id.tv_sub_content, sb.toString())

    }

}