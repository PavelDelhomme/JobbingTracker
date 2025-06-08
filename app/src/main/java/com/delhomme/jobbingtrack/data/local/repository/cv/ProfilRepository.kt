package com.delhomme.jobbingtrack.data.local.repository.cv

import com.delhomme.jobbingtrack.data.local.dao.cv.ProfileDao
import com.delhomme.jobbingtrack.data.local.entities.cv.ProfilEntity
import kotlinx.coroutines.flow.Flow


class ProfilRepository(private val dao: ProfileDao) {
    val all: Flow<List<ProfilEntity>>     = dao.getAll()
    fun byId(id: String): Flow<ProfilEntity?> = dao.getById(id)

    suspend fun byIdNow(id: String): ProfilEntity? = dao.getByIdNow(id)
    suspend fun save(profile: ProfilEntity) = dao.upsert(profile)
    suspend fun update(profile: ProfilEntity)= dao.update(profile)
    suspend fun delete(id: String)           = dao.deleteById(id)
    suspend fun deleteAll()                  = dao.deleteAll()
}