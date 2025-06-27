package com.delhomme.jobbingtrack.features.interview.data.repositories

import com.delhomme.jobbingtrack.core.utils.mapToInterview
import com.delhomme.jobbingtrack.core.utils.mapToInterviewStyle
import com.delhomme.jobbingtrack.core.utils.mapToInterviewType
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.features.interview.domain.model.Interview
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
    fun getAllActiveWithContacts(userId: String): Flow<List<InterviewWithContacts>> = dao.getAllWithContactsForUser(userId)
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
            val styles = styleEntities.map { mapToInterviewStyle(it) }
            val types = typeEntities.map { mapToInterviewType(it) }

            interviewsWithContacts.map { iwc ->
                val style = styles.find { it.id == iwc.interview.styleId }
                val type = types.find { it.id == iwc.interview.typeId }
                mapToInterview(iwc, style, type)
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