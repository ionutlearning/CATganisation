package com.example.catganisation.domain.usecase

import com.example.catganisation.domain.repository.CachedPrefsRepository
import javax.inject.Inject

class CachedTask @Inject constructor(
    private val repository: CachedPrefsRepository
) {

    fun isCached() = repository.isCached()
}
