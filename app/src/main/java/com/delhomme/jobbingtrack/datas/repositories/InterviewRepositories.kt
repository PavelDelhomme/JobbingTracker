package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewStatusDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewStyleDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewTypeDao
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStatusEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStyleEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewTypeEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewWithContacts
import com.delhomme.jobbingtrack.datas.mappers.toDomain
import com.delhomme.jobbingtrack.datas.models.Interview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


class InterviewRepository @Inject constructor(
    private val dao: InterviewDao
) {
    fun allForUser(userId: String): Flow<List<InterviewWithContacts>> = dao.getAllActiveForUser(userId)
    fun withContactsForUser(userId: String): Flow<List<InterviewWithContacts>> = dao.getAllWithContactsForUser(userId)
    fun archivedForUser(userId: String): Flow<List<InterviewEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<InterviewEntity>> = dao.getDeletedForUser(userId)
    fun byIdWithContacts(id: String, userId: String): Flow<InterviewWithContacts?> = dao.getByIdActiveWithContacts(id, userId)
    fun getActiveWithContacts(userId: String): Flow<List<InterviewWithContacts>> = dao.getAllWithContactsForUser(userId)
    fun activeForUser(
        userId: String,
        stylesFlow: Flow<List<InterviewStyleEntity>>,
        typesFlow: Flow<List<InterviewTypeEntity>>
    ): Flow<List<Interview>> =
        combine(
            withContactsForUser(userId),
            stylesFlow,
            typesFlow
        ) { interviewsWithContacts, styleEntities, typeEntities ->
            val styles = styleEntities.map { it.toDomain() }
            val types = typeEntities.map { it.toDomain() }
            interviewsWithContacts.map { iwc ->
                val style = styles.find { it.id == iwc.interview.styleId }
                val type = types.find { it.id == iwc.interview.typeId }
                iwc.toDomain(style, type)
            }
        }

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<InterviewEntity>> = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(entretien: InterviewEntity, contactIds: List<String>) {
        dao.upsert(entretien)
        dao.clearContactsFor(entretien.id)
        contactIds.forEach { dao.insertCrossRef(InterviewContactCrossRef(entretien.id, it)) }
    }
    suspend fun update(entretien: InterviewEntity) = dao.update(entretien)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)
}



class InterviewStatusRepository @Inject constructor(
    private val dao: InterviewStatusDao
) {
    val all: Flow<List<InterviewStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStatusEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewStatusEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewStatusEntity) = dao.delete(entity.id)
    suspend fun archive(id: String) = dao.archive(id)
    suspend fun restore(id: String) = dao.restore(id)
}


class InterviewStyleRepository @Inject constructor(
    private val dao: InterviewStyleDao
) {
    val all: Flow<List<InterviewStyleEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStyleEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewStyleEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewStyleEntity) = dao.delete(entity)
}


class InterviewTypeRepository @Inject constructor(
    private val dao: InterviewTypeDao
) {
    val all: Flow<List<InterviewTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewTypeEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewTypeEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewTypeEntity) = dao.delete(entity.id)
}
