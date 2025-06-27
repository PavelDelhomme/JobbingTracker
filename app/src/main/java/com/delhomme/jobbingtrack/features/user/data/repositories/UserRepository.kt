package com.delhomme.jobbingtrack.features.user.data.repositories

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val dao: UserDao
) {
    val all: Flow<List<UserEntity>> = dao.getAll()
    fun byId(id: String): Flow<UserEntity?> = dao.getById(id)
    suspend fun save(user: UserEntity) = dao.upsert(user)
    suspend fun update(user: UserEntity) = dao.update(user)
    suspend fun archive(id: String) = dao.archive(id)
    suspend fun delete(id: String) = dao.deleteById(id)
    suspend fun deleteAll() = dao.deleteAll()
}
