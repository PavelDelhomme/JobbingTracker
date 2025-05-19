package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.UserDao
import com.delhomme.jobbingtrack.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDao) {
    val users: Flow<List<UserEntity>> = dao.getAll()

    suspend fun save(user: UserEntity) {
        dao.insert(user)
    }

    suspend fun archive(id: String) {
        dao.archive(id)
    }

    suspend fun delete(id: String) {
        dao.deleteById(id)
    }
}