package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.ContractTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractTypeDao {
    @Query("SELECT * FROM contract_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<ContractTypeEntity>>

    @Query("SELECT * FROM contract_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ContractTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ContractTypeEntity)

    @Delete
    suspend fun delete(entity: ContractTypeEntity)
}
