package com.delhomme.jobbingtrack.datas.repositories

import androidx.lifecycle.ViewModel
import com.delhomme.jobbingtrack.datas.daos.calls.CallDao
import com.delhomme.jobbingtrack.datas.daos.calls.CallTypeDao
import com.delhomme.jobbingtrack.datas.entities.calls.CallEntity
import com.delhomme.jobbingtrack.datas.entities.calls.CallTypeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CallRepository @Inject constructor(
    private val dao: CallDao
) : ViewModel() {
    fun allForUser(userId: String): Flow<List<CallEntity>>         = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<CallEntity>>      = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<CallEntity>>    = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<CallEntity>>     = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<CallEntity?>        = dao.getByIdForUser(id, userId)

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<CallEntity>> = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(appel: CallEntity) = dao.upsert(appel)
    suspend fun update(appel: CallEntity) = dao.upsert(appel)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}


class CallTypeRepository @Inject constructor(
    private val dao: CallTypeDao
) {
    val all: Flow<List<CallTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: CallTypeEntity) = dao.save(entity)
    suspend fun delete(entity: CallTypeEntity) = dao.delete(entity)
}
