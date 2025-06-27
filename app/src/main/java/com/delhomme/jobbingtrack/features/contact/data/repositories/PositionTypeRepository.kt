package com.delhomme.jobbingtrack.features.contact.data.repositories

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PositionTypeRepository @Inject constructor(
    private val dao: PositionTypeDao
) {
    val all: Flow<List<PositionTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<PositionTypeEntity?> = dao.getById(id)
    suspend fun save(entity: PositionTypeEntity) = dao.save(entity)
    suspend fun delete(entity: PositionTypeEntity) = dao.delete(entity)
}
