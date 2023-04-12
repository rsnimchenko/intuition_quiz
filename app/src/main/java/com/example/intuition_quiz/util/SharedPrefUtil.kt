package com.example.intuition_quiz.util

import android.content.Context
import com.example.intuition_quiz.App

object SharedPrefUtil {
    private const val URL_KEY = "url"
    private val prefs = App.instance.getSharedPreferences(Constants.PREF_STOR_NAME, Context.MODE_PRIVATE)

    fun writeLastUrl(url: String) {
        val editor = prefs.edit()
        editor.putString(URL_KEY, url)
        editor.apply()
    }

    fun readLastUrl(): String {
        val prefs = App.instance.getSharedPreferences(Constants.PREF_STOR_NAME, Context.MODE_PRIVATE)
        return prefs.getString(URL_KEY, "").orEmpty()
    }
}