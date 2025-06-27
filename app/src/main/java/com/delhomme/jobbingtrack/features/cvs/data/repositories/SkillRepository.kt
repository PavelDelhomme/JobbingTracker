package com.delhomme.jobbingtrack.features.cvs.data.repositories


class SkillRepository @Inject constructor(
    private val dao: SkillDao
) {
    val all: Flow<List<SkillEntity>> = dao.getAll()
    fun byId(id: String): Flow<SkillEntity?> = dao.getById(id)
    suspend fun save(entity: SkillEntity) = dao.save(entity)
    suspend fun delete(entity: SkillEntity) = dao.delete(entity)
}