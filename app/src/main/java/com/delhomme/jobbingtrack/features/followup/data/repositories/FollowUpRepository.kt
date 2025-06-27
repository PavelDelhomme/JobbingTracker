package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpWithContacts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FollowUpRepository @Inject constructor(
    private val dao: FollowUpDao,
    private val typeDao: FollowUpTypeDao,
    private val statusDao: FollowUpStatusDao
) {
    fun allForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<FollowUpEntity?> = dao.getByIdForUser(id, userId)
    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<FollowUpEntity>> = dao.getByDateRangeForUser(userId, from, to)

    fun getAllActiveWithContacts(userId: String): Flow<List<FollowUpWithContacts>> =
        dao.getAllActiveWithContacts(userId)
    // Dans FollowUpRepository
    suspend fun save(followUp: FollowUpEntity, contactIds: List<String>) {
        // Sauvegarder le suivi
        dao.upsert(followUp)

        // Supprimer les anciennes relations
        dao.clearContactsForFollowUp(followUp.id)

        // Ajouter les nouvelles relations
        contactIds.forEach { contactId ->
            dao.insertFollowUpContactCrossRef(
                FollowUpContactCrossRef(followUpId = followUp.id, contactId = contactId)
            )
        }
    }

    suspend fun update(entity: FollowUpEntity) = dao.update(entity)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)

    suspend fun getContactIdsForFollowUp(followUpId: String): List<String> {
        return dao.getContactIdsForFollowUp(followUpId)
    }

    // Récupérer tous les types de suivi
    fun getAllFollowUpTypes(): Flow<List<FollowUpTypeEntity>> {
        return typeDao.getAll()
    }

    // Récupérer tous les statuts de suivi (qui remplacent les "responses")
    fun getAllFollowUpResponses(): Flow<List<FollowUpStatusEntity>> {
        return statusDao.getAll()
    }
}