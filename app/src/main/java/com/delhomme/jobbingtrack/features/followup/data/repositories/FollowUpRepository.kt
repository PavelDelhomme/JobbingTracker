package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpWithContacts
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
    fun byIdWithContacts(id: String, userId: String): Flow<FollowUpWithContacts?> = dao.getFollowUpWithContacts(id, userId)
    fun allActiveWithContacts(userId: String): Flow<List<FollowUpWithContacts>> = dao.getAllActiveWithContacts(userId)

    suspend fun save(followUp: FollowUpEntity) = dao.insert(followUp)
    suspend fun update(followUp: FollowUpEntity) = dao.update(followUp)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)
    suspend fun deleteAllForCompany(companyId: String) = dao.deleteAllForCompany(companyId)
    suspend fun deleteAllForApplication(applicationId: String) = dao.deleteAllForApplication(applicationId)

    suspend fun addContact(followUpId: String, contactId: String) =
        dao.insertFollowUpContactCrossRef(FollowUpContactCrossRef(followUpId, contactId))

    suspend fun clearContacts(followUpId: String) =
        dao.clearContactsForFollowUp(followUpId)

    suspend fun getContactIds(followUpId: String): List<String> =
        dao.getContactIdsForFollowUp(followUpId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}