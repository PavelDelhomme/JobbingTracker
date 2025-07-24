package com.delhomme.jobbingtrack.features.contact.data.repositories

import com.delhomme.jobbingtrack.features.contact.data.dao.ContactDao
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactWithCompany
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val dao: ContactDao
) {
    fun allForUser(userId: String): Flow<List<ContactEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<ContactEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<ContactEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<ContactEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<ContactEntity?> = dao.getByIdForUser(id, userId)
    fun byIdWithCompany(id: String, userId: String): Flow<ContactWithCompany?> = dao.getContactWithCompany(id, userId)
    fun allActiveWithCompany(userId: String): Flow<List<ContactWithCompany>> = dao.getAllActiveWithCompany(userId)

    suspend fun save(contact: ContactEntity) = dao.insert(contact)
    suspend fun update(contact: ContactEntity) = dao.update(contact)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ContactEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}