package com.delhomme.jobbingtrack.calls.repo

import com.delhomme.jobbingtrack.calls.CallTypeEntity
import com.delhomme.jobbingtrack.calls.dao.CallTypeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CallTypeRepository @Inject constructor(
    private val dao: CallTypeDao
) {
    val all: Flow<List<CallTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: CallTypeEntity) = dao.save(entity)
    suspend fun delete(entity: CallTypeEntity) = dao.delete(entity)
}
