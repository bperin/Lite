package com.brianperin.ddsample.util

import android.content.Context
import android.content.SharedPreferences

object Promos {

    private lateinit var prefs: SharedPreferences

    init {

    }

    fun setup(context: Context) {
        prefs = context.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
    }

    fun dismiss(id: String) {
        prefs.edit().putBoolean(id, true).commit()
    }

    fun isDismissed(id: String): Boolean {
        return prefs.getBoolean(id, false)
    }
}