package com.delhomme.jobbingtrack.followsup.repositories

import com.delhomme.jobbingtrack.followsup.dao.FollowUpPlateformDao
import com.delhomme.jobbingtrack.followsup.entities.FollowUpPlateformEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FollowUpPlatformRepository @Inject constructor(
    private val dao: FollowUpPlateformDao
) {
    val all: Flow<List<FollowUpPlateformEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpPlateformEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpPlateformEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpPlateformEntity) = dao.delete(entity)
}
