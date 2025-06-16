package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.datas.daos.contacts.ContactDao
import com.delhomme.jobbingtrack.datas.daos.contacts.DepartmentTypeDao
import com.delhomme.jobbingtrack.datas.daos.contacts.PositionTypeDao
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.DepartmentTypeEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.PositionTypeEntity
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


class DepartmentTypeRepository @Inject constructor(
    private val dao: DepartmentTypeDao
) {
    val all: Flow<List<DepartmentTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<DepartmentTypeEntity?> = dao.getById(id)
    suspend fun save(entity: DepartmentTypeEntity) = dao.save(entity)
    suspend fun delete(entity: DepartmentTypeEntity) = dao.delete(entity)
}


class PositionTypeRepository @Inject constructor(
    private val dao: PositionTypeDao
) {
    val all: Flow<List<PositionTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<PositionTypeEntity?> = dao.getById(id)
    suspend fun save(entity: PositionTypeEntity) = dao.save(entity)
    suspend fun delete(entity: PositionTypeEntity) = dao.delete(entity)
}
