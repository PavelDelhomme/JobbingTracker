package com.delhomme.jobbingtrack.core.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)

    @Query("UPDATE ${T::class.simpleName} SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)
}