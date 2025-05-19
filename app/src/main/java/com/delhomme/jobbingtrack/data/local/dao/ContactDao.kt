package com.delhomme.jobbingtrack.data.local.dao


import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    /** Tous les contacts non supprimés */
    @Query("SELECT * FROM contacts WHERE isDeleted = 0")
    fun getAll(): Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactEntity)

    @Query("UPDATE contacts SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE contacts SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}