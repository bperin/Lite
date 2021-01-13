package com.brianperin.ddsample.util

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

open class Cacheable<T>(context: Context, private val tClass: Class<T>) {
    var value: T? = null
    private val slapSharedPrefs: SharedPreferences = SlapSharedPrefs(context)
    val gson = Gson()
    var updated: Boolean = false
        private set
    init {
        val value: String = slapSharedPrefs.get(tClass.name)
        if (!TextUtils.isEmpty(value)) {
            try {
                this.value = gson.fromJson(value, tClass)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                clear()
            }
        }
    }
    fun get(): T? {
        return value
    }
    fun set(value: T?) {
        updated = true
        if (value == null) {
            this.value = null
            slapSharedPrefs.remove(tClass.name)
        } else {
            val customerString = gson.toJson(value)
            this.value = value
            slapSharedPrefs.put(tClass.name, customerString)
        }
    }
    fun clear() {
        updated = false
        this.value = null
        slapSharedPrefs.remove(tClass.name)
    }
}