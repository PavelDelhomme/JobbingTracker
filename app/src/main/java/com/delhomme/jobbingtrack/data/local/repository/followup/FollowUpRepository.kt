package com.delhomme.jobbingtrack.data.local.repository.followup

import androidx.lifecycle.LiveData
import com.delhomme.jobbingtrack.data.local.dao.followups.FollowUpDao
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpEntity
import kotlinx.coroutines.flow.Flow

class FollowUpRepository(private val dao: FollowUpDao) {

    /** Fluxs par userId */
    fun allForUser(userId: String): Flow<List<FollowUpEntity>>       = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<FollowUpEntity>>    = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<FollowUpEntity>>  = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<FollowUpEntity>>   = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<FollowUpEntity?>      = dao.getByIdForUser(id, userId)
    fun getAllForUser(userId: String): LiveData<List<FollowUpEntity>> {
        return dao.getAllForUser(userId) as LiveData<List<FollowUpEntity>>
    }

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<FollowUpEntity>> = dao.getByDateRangeForUser(userId, from, to)

    /** Insert ou replace */
    suspend fun save(relance: FollowUpEntity)                        = dao.upsert(relance)

    /** Mise à jour existante */
    suspend fun update(relance: FollowUpEntity)                      = dao.update(relance)

    /** Batch ops */
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)

    /** Tout vider */
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}
