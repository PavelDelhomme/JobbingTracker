package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpPlatformDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FollowUpPlatformRepository @Inject constructor(
    private val dao: FollowUpPlatformDao
) {
    val all: Flow<List<FollowUpPlateformEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpPlateformEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpPlateformEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpPlateformEntity) = dao.delete(entity)
}


class FollowUpStatusRepository @Inject constructor(
    private val dao: FollowUpStatusDao
) {
    val all: Flow<List<FollowUpStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpStatusEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpStatusEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpStatusEntity) = dao.delete(entity)
}


class FollowUpTypeRepository@Inject constructor(
    private val dao: FollowUpTypeDao
) {
    val all: Flow<List<FollowUpTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpTypeEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpTypeEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpTypeEntity) = dao.delete(entity)
}