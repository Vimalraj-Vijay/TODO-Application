package com.vimalraj.todoapplication.main

import android.content.Context
import android.content.Intent
import com.vimalraj.todoapplication.MainActivity
import com.vimalraj.todoapplication.movies.views.MoviesActivity
import com.vimalraj.todoapplication.multipleviewtype.view.MultipleViewTypeColumnActivity

fun launchSelectedScreen(context: Context, route: NavItemType) {
    when (route) {
        NavItemType.TODO -> {
            context.startActivity(Intent(context, MainActivity::class.java))
        }

        NavItemType.MOVIES -> {
            context.startActivity(Intent(context, MoviesActivity::class.java))
        }

        NavItemType.MULTI_TYPE_VIEWS -> {
            context.startActivity(Intent(context, MultipleViewTypeColumnActivity::class.java))
        }
    }
}