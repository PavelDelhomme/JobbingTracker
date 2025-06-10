package com.delhomme.jobbingtrack.cvs.repositories
import com.delhomme.jobbingtrack.cvs.dao.LanguageDao
import com.delhomme.jobbingtrack.cvs.entities.LanguageEntity
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
