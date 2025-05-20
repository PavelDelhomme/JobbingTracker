package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.EntretienContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import kotlinx.coroutines.flow.Flow

@Dao
interface EntretienDao {
    // 1) Tout (brut)
    @Transaction
    @Query("SELECT * FROM entretiens ORDER BY dateTime DESC")
    fun getAll(): Flow<List<EntretienEntity>>

    // 2) Actifs uniquement (non supprimés, non archivés)
    @Transaction
    @Query("""
        SELECT * FROM entretiens 
         WHERE isDeleted = 0 
           AND isArchived = 0 
         ORDER BY dateTime DESC
    """)
    fun getAllActive(): Flow<List<EntretienEntity>>

    // 3) Tous avec leurs contacts (même archivés)
    @Transaction
    @Query("SELECT * FROM entretiens WHERE isDeleted = 0 ORDER BY dateTime DESC")
    fun getAllWithContacts(): Flow<List<EntretienWithContacts>>

    // 4) Actifs avec leurs contacts
    @Transaction
    @Query("""
        SELECT * FROM entretiens 
         WHERE isDeleted = 0 
           AND isArchived = 0 
         ORDER BY dateTime DESC
    """)
    fun getAllActiveWithContacts(): Flow<List<EntretienWithContacts>>

    // 5) Supprimés (corbeille)
    @Transaction
    @Query("SELECT * FROM entretiens WHERE isDeleted = 1 ORDER BY dateTime DESC")
    fun getAllDeleted(): Flow<List<EntretienEntity>>

    // 6) Archivés
    @Transaction
    @Query("SELECT * FROM entretiens WHERE isArchived = 1 ORDER BY dateTime DESC")
    fun getAllArchived(): Flow<List<EntretienEntity>>

    // 7) Détail + contacts, quel que soit le statut
    @Transaction
    @Query("SELECT * FROM entretiens WHERE id = :id")
    fun getByIdWithContacts(id: String): Flow<EntretienWithContacts?>

    // 8) Entité seule par ID
    @Query("SELECT * FROM entretiens WHERE id = :id")
    fun getById(id: String): Flow<EntretienEntity?>

    // 9) (Optionnel) détail actif seulement
    @Transaction
    @Query("""
        SELECT * FROM entretiens 
         WHERE id = :id 
           AND isDeleted = 0 
           AND isArchived = 0
    """)
    fun getByIdActiveWithContacts(id: String): Flow<EntretienWithContacts?>

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