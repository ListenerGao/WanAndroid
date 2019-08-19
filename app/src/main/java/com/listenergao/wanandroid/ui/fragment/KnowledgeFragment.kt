package com.listenergao.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.adapter.KnowledgeAdapter
import com.listenergao.wanandroid.base.BaseFragment
import com.listenergao.wanandroid.http.response.BaseResponse
import com.listenergao.wanandroid.http.response.HomePageArticle
import com.listenergao.wanandroid.http.response.Knowledge
import com.listenergao.wanandroid.http.test.HttpUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * create on 19/07/29
 * 知识体系
 * @author ListenerGao
 */
class KnowledgeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swipe_layout)
    lateinit var mSwipeLayout: SwipeRefreshLayout
    @BindView(R.id.recycler_view)
    lateinit var mRecyclerView: RecyclerView

    lateinit var mAdapter: KnowledgeAdapter

    private val mKnowledgeObserver = object : Observer<BaseResponse<List<Knowledge>>> {
        override fun onComplete() {
            mSwipeLayout.isRefreshing = false
        }

        override fun onSubscribe(d: Disposable) {
            mSwipeLayout.isRefreshing = false

        }

        override fun onNext(response: BaseResponse<List<Knowledge>>) {
            mSwipeLayout.isRefreshing = false
            if (response.isSuccess()) {
                mAdapter.setNewData(response.data)
            }else if (response.hasErrorMsg()) {
                ToastUtils.showShort(response.errorMsg)
            }

        }

        override fun onError(e: Throwable) {
            mSwipeLayout.isRefreshing = false
            ToastUtils.showShort(e.message)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.knowledge_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSwipeLayout.setOnRefreshListener(this)

        qryData()
        initRecyclerView()
    }

    private fun qryData() {
        mSwipeLayout.isRefreshing = true
        HttpUtils.getInstance().getKnowledge(mKnowledgeObserver)
    }

    private fun initRecyclerView() {

        mAdapter = KnowledgeAdapter(emptyList())
        mRecyclerView.layoutManager = LinearLayoutManager(ActivityUtils.getTopActivity())
        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showShort("点击position = $position")
        }

    }

    override fun onRefresh() {
        qryData()
    }

}