package com.delhomme.jobbingtrack.data.local.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.local.dao.EntretienDao
import com.delhomme.jobbingtrack.data.local.entities.EntretienContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.utils.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EntretienRepository(private val dao: EntretienDao) {

    fun allForUser(userId: String): Flow<List<EntretienEntity>>     = dao.getAllActiveForUser(userId)
    fun withContactsForUser(userId: String): Flow<List<EntretienWithContacts>> = dao.getAllWithContactsForUser(userId)
    fun archivedForUser(userId: String): Flow<List<EntretienEntity>>            = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<EntretienEntity>>             = dao.getDeletedForUser(userId)
    fun byIdWithContacts(id: String, userId: String): Flow<EntretienWithContacts?> =
        dao.getByIdActiveWithContacts(id, userId)
    //fun activeForUser(userId: String): Flow<List<EntretienEntity>>      = dao.getAllActiveForUser(userId)

    fun activeForUser(userId: String): LiveData<List<Entretien>> =
        withContactsForUser(userId)
            .map { list -> list.map { it.toDomain() } }
            .asLiveData()

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<EntretienEntity>> = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(entretien: EntretienEntity, contactIds: List<String>) {
        dao.upsert(entretien)
        dao.clearContactsFor(entretien.id)
        contactIds.forEach { dao.insertCrossRef(EntretienContactCrossRef(entretien.id, it)) }
    }
    suspend fun update(entretien: EntretienEntity)                            = dao.update(entretien)
    suspend fun archive(ids: List<String>, userId: String)                   = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)                = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)                   = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)             = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                                    = dao.deleteAllForUser(userId)
}