package com.listenergao.wanandroid.ui.fragment

import android.os.Bundle
import android.util.Log
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
import com.chad.library.adapter.base.BaseQuickAdapter
import com.listenergao.wanandroid.R
import com.listenergao.wanandroid.adapter.HomeArticleAdapter
import com.listenergao.wanandroid.base.BaseFragment
import com.listenergao.wanandroid.http.response.BaseDataMode
import com.listenergao.wanandroid.http.response.BaseResponse
import com.listenergao.wanandroid.http.response.HomeBanner
import com.listenergao.wanandroid.http.response.HomePageArticle
import com.listenergao.wanandroid.http.test.HttpUtils
import com.listenergao.wanandroid.image.GlideImageLoader
import com.youth.banner.Banner
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * create on 19/07/29
 * 首页
 * @author ListenerGao
 */
class HomeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.swipe_layout)
    lateinit var mSwipeLayout: SwipeRefreshLayout
    @BindView(R.id.recycler_view)
    lateinit var mRecyclerView: RecyclerView

    //    @BindView(R.id.banner)
    lateinit var mBanner: Banner

    private var currentPage = "0"
    private lateinit var mAdapter: HomeArticleAdapter
    private var mHomeArticleList = arrayListOf<HomePageArticle>()
    private val mBannerImages = arrayListOf<String>()
    private val mBannerTitles = arrayListOf<String>()

    private val observer = object : Observer<BaseResponse<List<HomeBanner>>> {
        override fun onSubscribe(d: Disposable) {
            Log.d("gys", "onSubscribe....")
        }

        override fun onNext(response: BaseResponse<List<HomeBanner>>) {
            mSwipeLayout.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
            if (response.isSuccess()) {
                initOrUpdateBanner(response.data)
            }
        }

        override fun onError(e: Throwable) {
            mSwipeLayout.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
            Log.d("gys", "onError...")
        }

        override fun onComplete() {
            mSwipeLayout.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
            Log.d("gys", "onComplete...")
        }
    }

    private val homeArticleObserver = object : Observer<BaseResponse<BaseDataMode<ArrayList<HomePageArticle>>>> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onComplete() {
            mSwipeLayout.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
        }

        override fun onNext(response: BaseResponse<BaseDataMode<ArrayList<HomePageArticle>>>) {
            mSwipeLayout.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
            setRecyclerViewData(response)
        }

        override fun onError(e: Throwable) {
            mSwipeLayout.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSwipeLayout.setOnRefreshListener(this)
        Log.d("gys", "HomeFragment  onActivityCreated.....")
        init()
        qryData()
    }

    private fun init() {
        initRecyclerView()
    }

    private fun qryData() {
        currentPage = "0"
        mSwipeLayout.isRefreshing = true
        mAdapter.setEnableLoadMore(false)//这里的作用是防止下拉刷新的时候还可以上拉加载
        HttpUtils.getInstance().getHomeBanner(observer)
        HttpUtils.getInstance().getHomeArticle(currentPage, homeArticleObserver)
    }

    override fun onStart() {
        super.onStart()
        //开始自动播放
        mBanner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        //暂定自动播放
        mBanner.stopAutoPlay()
    }

    override fun onRefresh() {
        qryData()
    }


    private fun initOrUpdateBanner(bannerList: List<HomeBanner>?) {
        mBannerImages.clear()
        mBannerTitles.clear()
        bannerList?.forEach {
            it.imagePath?.let { imagePath -> mBannerImages.add(imagePath) }
//                it.desc?.let { desc -> mBannerTitles.add(desc) }
        }

        mBanner.setImages(mBannerImages)
//                .setBannerTitles(mBannerTitles)
            .setImageLoader(GlideImageLoader())
            //设置自动轮播，默认为true
            .isAutoPlay(true)
            //设置轮播时间
            .setDelayTime(3000)
            .start()
    }

    private fun initRecyclerView() {
        mAdapter = HomeArticleAdapter(mHomeArticleList)
        mAdapter.setOnLoadMoreListener(this, mRecyclerView)
        val headerView = LayoutInflater.from(ActivityUtils.getTopActivity())
            .inflate(R.layout.header_of_home_article_layout, mRecyclerView.parent as ViewGroup, false)
        mBanner = headerView.findViewById(R.id.banner)
        mAdapter.addHeaderView(headerView)
        mRecyclerView.layoutManager = LinearLayoutManager(ActivityUtils.getTopActivity())
        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showShort("点击position = $position")
        }

        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            ToastUtils.showShort("收藏功能待开发")
        }
    }

    /**
     * RecyclerView 上拉加载更多
     */
    override fun onLoadMoreRequested() {
        loadMore()
        Log.d("gys", "onLoadMoreRequested........")
    }

    private fun loadMore() {

        HttpUtils.getInstance().getHomeArticle(currentPage, homeArticleObserver)
    }

    /**
     * 处理列表数据
     * 包含列表的刷新以及上拉加载数据
     */
    private fun setRecyclerViewData(response: BaseResponse<BaseDataMode<ArrayList<HomePageArticle>>>) {
        val baseData = response.data
        if (response.isSuccess() && baseData != null) {
            currentPage = baseData.curPage.toString()
            if ("1" == currentPage) {
                Log.d("gys", "currentPage = $currentPage")
                //表示是在刷新数据,将之前数据清空
                mAdapter.setNewData(baseData.datas!!)
                return
            }

            Log.d("gys", "currentPage .......= $currentPage")
            mAdapter.addData(baseData.datas!!)
            //判断是否还有未加载数据
            if (baseData.curPage == baseData.pageCount - 1) {
                //数据加载完毕
                mAdapter.loadMoreEnd()
            } else {
                //本次加载完成
                mAdapter.loadMoreComplete()
            }
        } else {
            if ("0" == currentPage) {
                //首次初始化数据失败
            } else {
                //上拉加载数据失败
                mAdapter.loadMoreFail()
            }
        }
    }
}