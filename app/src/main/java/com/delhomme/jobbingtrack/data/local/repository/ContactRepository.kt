package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.ContactDao
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {
    val contacts: Flow<List<ContactEntity>> = contactDao.getAll()

    suspend fun save(contact: ContactEntity) = contactDao.insert(contact)
    suspend fun archive(id: String) = contactDao.archive(id)
    suspend fun delete(id: String) = contactDao.delete(id)
}