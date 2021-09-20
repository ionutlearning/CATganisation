package com.example.catganisation.data.repository

import com.example.catganisation.data.local.prefs.CachedPrefs
import com.example.catganisation.domain.repository.CachedPrefsRepository
import javax.inject.Inject

class CachedPrefsRepositoryImpl @Inject constructor(
    private val prefs: CachedPrefs,
) : CachedPrefsRepository {

    override fun isCached(): Boolean {
        return prefs.cached
    }
}
