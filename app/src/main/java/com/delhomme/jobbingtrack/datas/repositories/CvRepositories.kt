package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.datas.daos.cvs.CVDao
import com.delhomme.jobbingtrack.datas.daos.cvs.CollaboratorDao
import com.delhomme.jobbingtrack.datas.daos.cvs.EducationDao
import com.delhomme.jobbingtrack.datas.daos.cvs.ExperienceDao
import com.delhomme.jobbingtrack.datas.daos.cvs.LanguageDao
import com.delhomme.jobbingtrack.datas.daos.cvs.ProjectDao
import com.delhomme.jobbingtrack.datas.daos.cvs.SkillDao
import com.delhomme.jobbingtrack.datas.entities.cvs.CVEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.CollaboratorEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.EducationEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ExperienceEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.LanguageEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ProjectEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.SkillEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CvRepository @Inject constructor(
    private val dao: CVDao
) {
    val all: Flow<List<CVEntity>> = dao.getAll()
    fun byId(id: String): Flow<CVEntity?> = dao.getById(id)
    suspend fun save(entity: CVEntity) = dao.save(entity)
    suspend fun delete(entity: CVEntity) = dao.delete(entity)

    fun getByUserId(userId: String) = dao.getByUserId(userId)
}


class CollaboratorRepository @Inject constructor(
    private val dao: CollaboratorDao
) {
    val all: Flow<List<CollaboratorEntity>> = dao.getAll()
    fun byId(id: String): Flow<CollaboratorEntity?> = dao.getById(id)
    suspend fun save(entity: CollaboratorEntity) = dao.save(entity)
    suspend fun update(entity: CollaboratorEntity) = dao.update(entity)
    suspend fun delete(entity: CollaboratorEntity) = dao.delete(entity)
}


class EducationRepository @Inject constructor(
    private val dao: EducationDao
) {
    val all: Flow<List<EducationEntity>> = dao.getAll()
    fun byId(id: String): Flow<EducationEntity?> = dao.getById(id)
    suspend fun save(entity: EducationEntity) = dao.save(entity)
    suspend fun delete(entity: EducationEntity) = dao.delete(entity)
}


class ExperienceRepository @Inject constructor(
    private val dao: ExperienceDao
) {
    val all: Flow<List<ExperienceEntity>> = dao.getAll()
    fun byId(id: String): Flow<ExperienceEntity?> = dao.getById(id)
    suspend fun save(entity: ExperienceEntity) = dao.save(entity)
    suspend fun update(entity: ExperienceEntity) = dao.update(entity)
    suspend fun delete(entity: ExperienceEntity) = dao.delete(entity)
}


class LanguageRepository @Inject constructor(
    private val dao: LanguageDao
) {
    val all: Flow<List<LanguageEntity>> = dao.getAll()
    fun byId(id: String): Flow<LanguageEntity?> = dao.getById(id)
    suspend fun save(entity: LanguageEntity) = dao.save(entity)
    suspend fun delete(entity: LanguageEntity) = dao.delete(entity)
}


class ProjectRepository @Inject constructor(
    private val dao: ProjectDao
) {
    val all: Flow<List<ProjectEntity>> = dao.getAll()
    fun byId(id: String): Flow<ProjectEntity?> = dao.getById(id)
    suspend fun save(entity: ProjectEntity) = dao.save(entity)
    suspend fun delete(entity: ProjectEntity) = dao.delete(entity)
}


class SkillRepository @Inject constructor(
    private val dao: SkillDao
) {
    val all: Flow<List<SkillEntity>> = dao.getAll()
    fun byId(id: String): Flow<SkillEntity?> = dao.getById(id)
    suspend fun save(entity: SkillEntity) = dao.save(entity)
    suspend fun delete(entity: SkillEntity) = dao.delete(entity)
}
