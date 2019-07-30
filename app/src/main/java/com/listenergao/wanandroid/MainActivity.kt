package com.listenergao.wanandroid

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.listenergao.wanandroid.adapter.MainPagerAdapter
import com.listenergao.wanandroid.base.BaseActivity

/**
 * create on 19/07/29
 * 主页面，包含首页，知识体系，导航，以及项目四个Fragment
 *
 * @author ListenerGao
 */
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.viewPager)
    lateinit var mViewPager: ViewPager
    @BindView(R.id.bottom_navigation_view)
    lateinit var mBottomView: BottomNavigationView

    private lateinit var mMainAdapter: MainPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        initFragment()
    }

    private fun initFragment() {
        mMainAdapter = MainPagerAdapter(supportFragmentManager)
        mMainAdapter.setItems(
            arrayListOf(
                MainScreen.HOME,
                MainScreen.KNOWLEDGE,
                MainScreen.NAVIGATION,
                MainScreen.PROJECT
            )
        )

        // 显示默认的页面-首页
        val defaultScreen = MainScreen.HOME
        scrollToScreen(defaultScreen)
        selectBottomNavigationViewMenuItem(defaultScreen.menuItemId)
        supportActionBar?.setTitle(defaultScreen.titleStringId)

        initBottomNavigationView()

    }

    /**
     * 初始化BottomNavigationView，并与ViewPager关联
     */
    private fun initBottomNavigationView() {
        mBottomView.setOnNavigationItemSelectedListener(this)

        mViewPager.adapter = mMainAdapter
        mViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val selectedScreen = mMainAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
                supportActionBar?.setTitle(selectedScreen.titleStringId)
            }
        })
    }

    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = mMainAdapter.getItems().indexOf(mainScreen)
        if (screenPosition != mViewPager.currentItem) {
            mViewPager.currentItem = screenPosition
        }
    }

    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        mBottomView.setOnNavigationItemSelectedListener(null)
        mBottomView.selectedItemId = menuItemId
        mBottomView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        getMainScreenForMenuItem(menuItem.itemId)?.let {
            scrollToScreen(it)
            supportActionBar?.setTitle(it.titleStringId)
            return true
        }
        return false
    }

}
