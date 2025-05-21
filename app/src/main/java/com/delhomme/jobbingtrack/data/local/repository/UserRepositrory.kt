package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.UserDao
import com.delhomme.jobbingtrack.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDao) {
    val all: Flow<List<UserEntity>>         = dao.getAll()
    fun byId(id: String): Flow<UserEntity?> = dao.getById(id)

    suspend fun save(user: UserEntity)     = dao.upsert(user)
    suspend fun update(user: UserEntity)   = dao.update(user)
    suspend fun delete(id: String)         = dao.deleteById(id)
    suspend fun deleteAll()                = dao.deleteAll()
}