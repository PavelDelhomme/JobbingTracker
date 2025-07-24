package com.delhomme.jobbingtrack.core.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.delhomme.jobbingtrack.core.model.BaseEntity

interface BaseDao<T : BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>): List<Long>

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun updateAll(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun deleteAll(entities: List<T>)

    // Récupérer une entité par ID
    suspend fun getById(id: String, userId: String): T?

    // Récupérer les entités mises à jour depuis un timestamp
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<T>

    // Mettre à jour le timestamp de synchronisation des entités
    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    // Marquer une entité comme supprimée (soft delete)
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}