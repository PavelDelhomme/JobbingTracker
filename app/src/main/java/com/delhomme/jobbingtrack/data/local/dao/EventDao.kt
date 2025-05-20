package com.delhomme.jobbingtrack.data.local.dao


import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    
    // 1) Tous les  v nements (m me archiv s ou supprim s)
    @Query("SELECT * FROM events ORDER BY startDate DESC")
    fun getAll(): Flow<List<EventEntity>>
    
    // 2)  v nements actifs (ni supprim s, ni archiv s)
    @Query("SELECT * FROM events WHERE isDeleted = 0 AND isArchived = 0 ORDER BY startDate DESC")
    fun getAllActive(): Flow<List<EventEntity>>
    
    // 3)  v nements archiv s
    @Query("SELECT * FROM events WHERE isArchived = 1 ORDER BY startDate DESC")
    fun getAllArchived(): Flow<List<EventEntity>>
    
    // 4)  v nements supprim s (corbeille)
    @Query("SELECT * FROM events WHERE isDeleted = 1 ORDER BY startDate DESC")
    fun getAllDeleted(): Flow<List<EventEntity>>
    
    // 5) D tail d'un  v nement par son id
    @Query("SELECT * FROM events WHERE id = :id")
    fun getById(id: String): Flow<EventEntity>
    
    // Inserts / updates
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    // 6) R cup ration des  v nements par type
    @Query("SELECT * FROM events WHERE type = :type ORDER BY startDate DESC")
    fun getAllByType(type: String): Flow<List<EventEntity>>

    // 7) R cup ration des  v nements par plage de date
    @Query("SELECT * FROM events WHERE startDate >= :startDate AND startDate < :endDate ORDER BY startDate DESC")
    fun getAllByDateRange(startDate: Long, endDate: Long): Flow<List<EventEntity>>

    // 8) R cup ration des  v nements par date
    @Query("SELECT * FROM events WHERE startDate = :date ORDER BY startDate DESC")
    fun getAllByDate(date: Long): Flow<List<EventEntity>>

    // 9) R cup ration des  v nements par relatedObjectId
    @Query("SELECT * FROM events WHERE relatedObjectId = :relatedObjectId ORDER BY startDate DESC")
    fun getAllByRelatedObjectId(relatedObjectId: String): Flow<List<EventEntity>>

    // 10) R cup ration des  v nements par userId
    @Query("SELECT * FROM events WHERE userId = :userId ORDER BY startDate DESC")
    fun getAllByUserId(userId: String): Flow<List<EventEntity>>

    // Archivage / suppression douce
    @Update
    suspend fun archive(id: String)
    
    @Update
    suspend fun delete(id: String)
}