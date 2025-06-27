package com.delhomme.jobbingtrack.etc.core.repository

import com.delhomme.jobbingtrack.core.database.BaseDao

abstract class BaseRepository<T : Any>(private val dao: BaseDao<T>) {
    suspend fun save(entity: T) = dao.insert(entity)
    suspend fun update(entity: T) = dao.update(entity)
    suspend fun delete(entity: T) = dao.delete(entity)
    // Ajoute d'autres méthodes communes...
}
