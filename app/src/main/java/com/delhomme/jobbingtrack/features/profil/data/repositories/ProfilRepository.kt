package com.delhomme.jobbingtrack.features.profil.data.repositories

import com.delhomme.jobbingtrack.features.profil.data.dao.ProfilDao
import com.delhomme.jobbingtrack.features.profil.data.entities.ProfilEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProfilRepository @Inject constructor(private val dao: ProfilDao) {
    val all: Flow<List<ProfilEntity>>     = dao.getAll()
    fun byId(id: String): Flow<ProfilEntity?> = dao.getById(id)

    suspend fun byIdNow(id: String): ProfilEntity? = dao.getByIdNow(id)
    suspend fun save(profile: ProfilEntity) = dao.upsert(profile)
    suspend fun update(profile: ProfilEntity)= dao.update(profile)
    suspend fun delete(id: String)           = dao.deleteById(id)
    suspend fun deleteAll()                  = dao.deleteAll()
}