package com.delhomme.jobbingtrack.features.contact.data.repositories

import com.delhomme.jobbingtrack.features.contact.data.dao.ContactDao
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ContactRepository @Inject constructor(
    private val dao: ContactDao
) {
    fun allForUser(userId: String): Flow<List<ContactEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<ContactEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<ContactEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<ContactEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<ContactEntity?> = dao.getByIdForUser(id, userId)

    suspend fun save(contact: ContactEntity)                = dao.upsert(contact)
    suspend fun update(contact: ContactEntity)              = dao.upsert(contact)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}
