package com.delhomme.jobbingtrack.datas.daos.applications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ContractTypeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationTypeDao {
    @Query("SELECT * FROM application_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<ContractTypeEntity>>

    @Query("SELECT * FROM application_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ContractTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ContractTypeEntity)

    @Delete
    suspend fun delete(entity: ContractTypeEntity)
}