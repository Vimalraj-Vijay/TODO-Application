package com.vimalraj.todoapplication.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vimalraj.todoapplication.R

data class MainNavigationItems(
    @StringRes
    val title: Int,
    @DrawableRes
    val icon: Int,
    val isSelected: Boolean,
    val route: NavItemType
)

fun getMainNavigationItems(): List<MainNavigationItems> {
    return listOf(
        MainNavigationItems(
            title = R.string.todo_drawer_title,
            icon = R.drawable.ic_todo,
            isSelected = false,
            route = NavItemType.TODO
        ),

        MainNavigationItems(
            title = R.string.movies_drawer_title,
            icon = R.drawable.ic_movies,
            isSelected = false,
            route = NavItemType.MOVIES
        ),

        MainNavigationItems(
            title = R.string.mutliple_view_drawer_title,
            icon = R.drawable.ic_multiview,
            isSelected = false,
            route = NavItemType.MULTI_TYPE_VIEWS
        ),

        MainNavigationItems(
            title = R.string.learn_title,
            icon = R.drawable.ic_learn,
            isSelected = false,
            route = NavItemType.LEARN
        ),
    )
}



