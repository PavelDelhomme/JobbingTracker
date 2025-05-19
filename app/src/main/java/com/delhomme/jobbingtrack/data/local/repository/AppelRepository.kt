package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.AppelDao
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.flow.Flow

class AppelRepository(private val dao: AppelDao) {

    /** Flux de tous les appels non supprimés */
    val appels: Flow<List<AppelEntity>> = dao.getAll()

    suspend fun save(appel: AppelEntity) = dao.insert(appel)
    suspend fun archive(id: String) = dao.archive(id)
    suspend fun delete(id: String) = dao.delete(id)
}