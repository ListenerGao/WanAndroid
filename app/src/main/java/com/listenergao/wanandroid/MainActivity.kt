package com.listenergao.wanandroid

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.listenergao.wanandroid.adapter.MainPagerAdapter
import com.listenergao.wanandroid.base.BaseActivity

/**
 * create on 19/07/29
 * 主页面，包含首页，知识体系，导航，以及项目四个Fragment
 *
 * @author ListenerGao
 */

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.drawer_layout)
    lateinit var mDrawerLayout: DrawerLayout
    @BindView(R.id.nav_view)
    lateinit var mNavigationView: NavigationView
    @BindView(R.id.viewPager)
    lateinit var mViewPager: ViewPager
    @BindView(R.id.bottom_navigation_view)
    lateinit var mBottomView: BottomNavigationView

    private lateinit var mMainAdapter: MainPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        initView()

    }

    private fun initView() {
        setSupportActionBar(mToolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView.setNavigationItemSelectedListener(this)

        // TODO 夜间模式与日间模式图标文字的设置
//        val modeSwitchItem = mNavigationView.menu.findItem(R.id.nav_mode_switch)
//        modeSwitchItem.setIcon(R.drawable.icon_daytime_mode)
//        modeSwitchItem.setTitle(R.string.menu_daytime_mode)

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
        //初始化时，直接使用mToolbar..setTitle(defaultScreen.titleStringId)未生效，原因暂未找到
        supportActionBar?.setTitle(defaultScreen.titleStringId)

        initBottomNavigationView()

    }

    /**
     * 初始化BottomNavigationView，并与ViewPager关联
     */
    private fun initBottomNavigationView() {
        mBottomView.setOnNavigationItemSelectedListener(this)
        mViewPager.adapter = mMainAdapter
        mViewPager.offscreenPageLimit = 4
        mViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val selectedScreen = mMainAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
                mToolbar.setTitle(selectedScreen.titleStringId)
            }
        })
    }

    /**
     * 滑动到当前选择的页面
     */
    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = mMainAdapter.getItems().indexOf(mainScreen)
        if (screenPosition != mViewPager.currentItem) {
            mViewPager.currentItem = screenPosition
        }
    }

    /**
     * 设置当前选中的Bottom Item
     */
    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
//        mBottomView.setOnNavigationItemSelectedListener(null)
        mBottomView.selectedItemId = menuItemId

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // 处理主页面 首页，知识体系，导航，项目四个Item点击事件
        getMainScreenForMenuItem(item.itemId)?.let {
            scrollToScreen(it)
            supportActionBar?.setTitle(it.titleStringId)
            return true
        }

        // 处理DrawLayout页面Item点击事件
        when (item.itemId) {
            //收藏页面
            R.id.nav_collection -> {
                // Handle the camera action
            }
            //TODO页面
            R.id.nav_todo -> {

            }
            //夜间，日间模式切换
            R.id.nav_mode_switch -> {
//                item.setIcon(R.drawable.icon_daytime_mode)
//                item.setTitle(R.string.menu_daytime_mode)
            }
            //设置页面
            R.id.nav_settings -> {

            }
            //关于我们页面
            R.id.nav_about -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
