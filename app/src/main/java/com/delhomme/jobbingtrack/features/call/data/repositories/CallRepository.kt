package com.delhomme.jobbingtrack.features.call.data.repositories

import com.delhomme.jobbingtrack.features.call.data.dao.CallDao
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallWithContacts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallRepository @Inject constructor(
    private val dao: CallDao
) {
    fun allForUser(userId: String): Flow<List<CallEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<CallEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<CallEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<CallEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<CallEntity?> = dao.getByIdForUser(id, userId)
    fun byIdWithContacts(id: String, userId: String): Flow<CallWithContacts?> = dao.getCallWithContacts(id, userId)
    fun allActiveWithContacts(userId: String): Flow<List<CallWithContacts>> = dao.getAllActiveWithContacts(userId)

    suspend fun save(call: CallEntity): Long = dao.insert(call)
    suspend fun update(call: CallEntity) = dao.update(call)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CallEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)

    // Helper method for CallViewModel
    fun getByDateRange(userId: String, fromTimestamp: Long, toTimestamp: Long): Flow<List<CallEntity>> {
        val query = androidx.sqlite.db.SimpleSQLiteQuery(
            "SELECT * FROM calls WHERE userId = ? AND timestamp BETWEEN ? AND ? AND isDeleted = 0 ORDER BY timestamp DESC",
            arrayOf(userId, fromTimestamp, toTimestamp)
        )
        return dao.getByDateRange(query)
    }
}