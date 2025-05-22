package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.RelanceDao
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import kotlinx.coroutines.flow.Flow

class RelanceRepository(private val dao: RelanceDao) {

    /** Fluxs par userId */
    fun allForUser(userId: String): Flow<List<RelanceEntity>>       = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<RelanceEntity>>    = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<RelanceEntity>>  = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<RelanceEntity>>   = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<RelanceEntity?>      = dao.getByIdForUser(id, userId)

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<RelanceEntity>> = dao.getByDateRangeForUser(userId, from, to)

    /** Insert ou replace */
    suspend fun save(relance: RelanceEntity)                        = dao.upsert(relance)

    /** Mise à jour existante */
    suspend fun update(relance: RelanceEntity)                      = dao.update(relance)

    /** Batch ops */
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)

    /** Tout vider */
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}
