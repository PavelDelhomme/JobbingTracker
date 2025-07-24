package com.delhomme.jobbingtrack.features.call.data.repositories

import com.delhomme.jobbingtrack.features.call.data.dao.CallTypeDao
import com.delhomme.jobbingtrack.features.call.data.entities.CallTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallTypeRepository @Inject constructor(
    private val dao: CallTypeDao
) {
    val all: Flow<List<CallTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<CallTypeEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<CallTypeEntity>> = dao.getAllForUser(userId)

    suspend fun save(callType: CallTypeEntity) = dao.insert(callType)
    suspend fun update(callType: CallTypeEntity) = dao.update(callType)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CallTypeEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}