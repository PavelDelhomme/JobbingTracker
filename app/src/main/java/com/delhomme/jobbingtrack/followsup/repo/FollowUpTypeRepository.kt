package com.delhomme.jobbingtrack.followsup.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FollowUpTypeRepository@Inject constructor(
    private val dao: FollowUpTypeDao
) {
    val all: Flow<List<FollowUpTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpTypeEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpTypeEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpTypeEntity) = dao.delete(entity)
}