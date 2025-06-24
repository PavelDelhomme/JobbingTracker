package com.delhomme.jobbingtrack.followsup.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FollowUpStatusRepository @Inject constructor(
    private val dao: FollowUpStatusDao
) {
    val all: Flow<List<FollowUpStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpStatusEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpStatusEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpStatusEntity) = dao.delete(entity)
}
