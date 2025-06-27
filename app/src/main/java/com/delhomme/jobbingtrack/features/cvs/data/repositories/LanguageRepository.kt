package com.delhomme.jobbingtrack.features.cvs.data.repositories

import com.delhomme.jobbingtrack.features.cvs.data.entities.LanguageEntity
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.LanguageDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LanguageRepository @Inject constructor(
    private val dao: LanguageDao
) {
    val all: Flow<List<LanguageEntity>> = dao.getAll()
    fun byId(id: String): Flow<LanguageEntity?> = dao.getById(id)
    suspend fun save(entity: LanguageEntity) = dao.save(entity)
    suspend fun delete(entity: LanguageEntity) = dao.delete(entity)
}