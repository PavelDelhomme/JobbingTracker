package com.delhomme.jobbingtrack.data.local.dao.applications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.data.local.entities.application.ContractTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractTypeDao {
    @Query("SELECT * FROM contract_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<ContractTypeEntity>>

    @Query("SELECT * FROM contract_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ContractTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: ContractTypeEntity)

    @Delete
    suspend fun delete(entity: ContractTypeEntity)
}