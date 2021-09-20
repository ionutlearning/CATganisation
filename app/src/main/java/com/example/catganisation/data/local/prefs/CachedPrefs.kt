package com.example.catganisation.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CachedPrefs @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val CACHED_PREFS = "CACHED_PREFS"
        private const val IS_CACHED = "IS_CACHED"

    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(CACHED_PREFS, Context.MODE_PRIVATE)

    var cached: Boolean
        get() = preferences.getBoolean(IS_CACHED, false)
        set(value) = preferences.edit().putBoolean(IS_CACHED, value).apply()
}
