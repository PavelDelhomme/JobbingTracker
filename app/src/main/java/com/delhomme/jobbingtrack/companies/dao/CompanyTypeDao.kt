package com.delhomme.jobbingtrack.companies.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.companies.CompanyTypeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CompanyTypeDao {
    @Query("SELECT * FROM company_types")
    fun getAll(): Flow<List<CompanyTypeEntity>>

    @Query("SELECT * FROM company_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<CompanyTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CompanyTypeEntity)


    @Delete
    suspend fun delete(entity: CompanyTypeEntity)
}
