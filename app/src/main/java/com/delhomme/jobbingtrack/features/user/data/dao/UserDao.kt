package com.delhomme.jobbingtrack.features.user.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.user.data.entities.UserEntity
import com.delhomme.jobbingtrack.features.user.data.entities.UserWithRelations
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao : BaseDao<UserEntity> {

    @Transaction
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getWithRelations(id: String): UserWithRelations?

    /** Toutes les users */
    @Query("SELECT * FROM users ORDER BY email")
    fun getAll(): Flow<List<UserEntity>>

    /** Détail */
    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: String): Flow<UserEntity?>

    /** Insert ou update */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: UserEntity)

    /** Mise à jour (tous champs) */
    @Update
    override suspend fun update(user: UserEntity)

    /** Archive */
    @Query("UPDATE users SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    /** Suppression définitive */
    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteById(id: String)

    /** Vider tout (rarement utilisé) */
    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
