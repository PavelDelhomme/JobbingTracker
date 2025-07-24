package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpWithRelations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowUpRepository @Inject constructor(
    private val dao: FollowUpDao
) {
    fun allForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<FollowUpEntity?> = dao.getByIdForUser(id, userId)

    suspend fun getWithRelations(id: String): FollowUpWithRelations? = dao.getWithRelations(id)

    fun getByApplicationId(applicationId: String): Flow<List<FollowUpEntity>> = dao.getByApplicationId(applicationId)
    fun getByCompanyId(companyId: String): Flow<List<FollowUpEntity>> = dao.getByCompanyId(companyId)
    fun getByContactId(contactId: String): Flow<List<FollowUpEntity>> = dao.getByContactId(contactId)

    suspend fun save(followUp: FollowUpEntity): Long = dao.insert(followUp)
    suspend fun update(followUp: FollowUpEntity) = dao.update(followUp)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)

    fun getRemindersForDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<FollowUpEntity>> =
        dao.getRemindersForDateRange(userId, startDate, endDate)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)

    // Helper method pour DateRangeProvider
    fun getByDateRange(userId: String, fromTimestamp: Long, toTimestamp: Long): Flow<List<FollowUpEntity>> {
        val query = androidx.sqlite.db.SimpleSQLiteQuery(
            "SELECT * FROM follow_ups WHERE userId = ? AND date BETWEEN ? AND ? AND isDeleted = 0 ORDER BY date DESC",
            arrayOf(userId, fromTimestamp, toTimestamp)
        )
        return dao.getByDateRange(query)
    }
}