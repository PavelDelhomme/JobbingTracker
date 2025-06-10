package com.delhomme.jobbingtrack.contacts.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.contacts.entities.DepartmentTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentTypeDao {
    @Query("SELECT * FROM department_types")
    fun getAll(): Flow<List<DepartmentTypeEntity>>

    @Query("SELECT * FROM department_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<DepartmentTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: DepartmentTypeEntity)

    @Delete
    suspend fun delete(entity: DepartmentTypeEntity)
}
