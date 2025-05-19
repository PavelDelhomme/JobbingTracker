package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import kotlinx.coroutines.flow.Flow


@Dao
interface EntretienDao {
    @Transaction
    @Query("SELECT * FROM entretiens WHERE isDeleted = 0 ORDER BY dateTime DESC")
    fun getAllWithContacts(): Flow<List<EntretienWithContacts>>

    @Transaction
    @Query("SELECT * FROM entretiens WHERE id = :id")
    fun getByIdWithContacts(id: String): Flow<EntretienWithContacts?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entretien: EntretienEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCrossRef(ref: EntretienContactCrossRef)

    @Query("DELETE FROM EntretienContactCrossRef WHERE entretienId = :entretienId")
    suspend fun clearContactsFor(entretienId: String)

    @Query("UPDATE entretiens SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE entretiens SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}