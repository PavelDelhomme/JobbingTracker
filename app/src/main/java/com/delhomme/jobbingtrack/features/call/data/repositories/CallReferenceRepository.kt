package com.delhomme.jobbingtrack.features.call.data.repositories

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
