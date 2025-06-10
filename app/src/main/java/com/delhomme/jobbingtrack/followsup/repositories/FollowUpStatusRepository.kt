package com.delhomme.jobbingtrack.followsup.repositories

import com.delhomme.jobbingtrack.followsup.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.followsup.entities.FollowUpStatusEntity
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
