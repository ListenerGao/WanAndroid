package com.listenergao.wanandroid

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.listenergao.wanandroid.ui.fragment.HomeFragment
import com.listenergao.wanandroid.ui.fragment.KnowledgeFragment
import com.listenergao.wanandroid.ui.fragment.NavigationFragment
import com.listenergao.wanandroid.ui.fragment.ProjectFragment

enum class MainScreen(
    @IdRes val menuItemId: Int,
    @DrawableRes val menuItemIconId: Int,
    @StringRes val titleStringId: Int,
    val fragment: Fragment
) {
    HOME(
        R.id.item_home,
        R.drawable.home,
        R.string.home,
        HomeFragment()
    ),
    KNOWLEDGE(
        R.id.item_knowledge,
        R.drawable.knowledge,
        R.string.knowledge,
        KnowledgeFragment()
    ),
    NAVIGATION(
        R.id.item_navigation,
        R.drawable.navigation,
        R.string.navigation,
        NavigationFragment()
    ),
    PROJECT(
        R.id.item_project,
        R.drawable.project,
        R.string.project,
        ProjectFragment()
    )
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}

