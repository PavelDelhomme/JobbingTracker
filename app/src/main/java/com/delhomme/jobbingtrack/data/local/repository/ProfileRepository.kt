package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.ProfileDao
import com.delhomme.jobbingtrack.data.local.entities.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val dao: ProfileDao) {
    val profiles: Flow<List<ProfileEntity>> = dao.getAll()

    suspend fun save(profile: ProfileEntity) {
        dao.insert(profile)
    }

    suspend fun archive(id: String) {
        dao.archive(id)
    }

    suspend fun delete(id: String) {
        dao.deleteById(id)
    }
}