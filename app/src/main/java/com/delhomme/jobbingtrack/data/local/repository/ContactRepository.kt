package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.ContactDao
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val dao: ContactDao) {
    val all     : Flow<List<ContactEntity>> = dao.getAll()
    val active  : Flow<List<ContactEntity>> = dao.getAllActive()
    val archived: Flow<List<ContactEntity>> = dao.getAllArchived()
    val deleted : Flow<List<ContactEntity>> = dao.getAllDeleted()

    fun forUser(userId: String): Flow<List<ContactEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<ContactEntity>> = dao.getActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<ContactEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<ContactEntity>> = dao.getDeletedForUser(userId)

    suspend fun insert(contact: ContactEntity) = dao.insert(contact)
    suspend fun archive(id: String) = dao.archive(id)
    suspend fun delete(id: String) = dao.delete(id)
}