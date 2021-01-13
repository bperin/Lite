package com.brianperin.ddsample.util

import android.content.Context
import android.content.SharedPreferences

fun SlapSharedPrefs(context: Context): SharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
// Modified type safe shared prefs based on
// https://blogs.naxam.net/sharedpreferences-made-easy-with-kotlin-generics-extensions-6189d8902fb0
// https://medium.com/@krupalshah55/manipulating-shared-prefs-with-kotlin-just-two-lines-of-code-29af62440285
/**
 * @param defaultValue if not specified or null, depending on type will return an empty string, false, or -1
 */
inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T? = null): T {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> {
            // TODO support string sets without supplying default value
            if (defaultValue is Set<*>) {
                getStringSet(key, defaultValue as? Set<String> ?: emptySet()) as T
            } else {
                throw UnsupportedOperationException("No shared pref support for ${T::class}")
            }
        }
    }
}
inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()
    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            } else {
                throw UnsupportedOperationException("No shared pref support for ${T::class}")
            }
        }
    }
    editor.apply()
}
inline fun <reified T> SharedPreferences.putAndCommit(key: String, value: T) {
    val editor = this.edit()
    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            } else {
                throw UnsupportedOperationException("No shared pref support for ${T::class}")
            }
        }
    }
    editor.commit()
}
fun SharedPreferences.putAndCommit(key: String, value: String) {
    val editor = this.edit()
    editor.putString(key, value)
    editor.commit()
}
fun SharedPreferences.remove(key: String) {
    val editor = this.edit()
    editor.remove(key)
    editor.apply()
}
// Temporary helpers for Java code (which doesn't support reified type)
fun SharedPreferences.put(key: String, value: String) {
    val editor = this.edit()
    editor.putString(key, value)
    editor.apply()
}