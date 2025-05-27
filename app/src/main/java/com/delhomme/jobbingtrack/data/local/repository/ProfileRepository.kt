package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.ProfileDao
import com.delhomme.jobbingtrack.data.local.entities.ProfileEntity
import kotlinx.coroutines.flow.Flow


class ProfileRepository(private val dao: ProfileDao) {
    val all: Flow<List<ProfileEntity>>     = dao.getAll()
    fun byId(id: String): Flow<ProfileEntity?> = dao.getById(id)

    suspend fun byIdNow(id: String): ProfileEntity? = dao.getByIdNow(id)
    suspend fun save(profile: ProfileEntity) = dao.upsert(profile)
    suspend fun update(profile: ProfileEntity)= dao.update(profile)
    suspend fun delete(id: String)           = dao.deleteById(id)
    suspend fun deleteAll()                  = dao.deleteAll()
}