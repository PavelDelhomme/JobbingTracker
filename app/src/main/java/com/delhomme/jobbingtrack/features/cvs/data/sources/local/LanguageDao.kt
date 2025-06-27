package com.delhomme.jobbingtrack.features.cvs.data.sources.local


@Dao
interface LanguageDao {
    @Query("SELECT * FROM languages WHERE isDeleted = 0")
    fun getAll(): Flow<List<LanguageEntity>>

    @Query("SELECT * FROM languages WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<LanguageEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: LanguageEntity)

    @Delete
    suspend fun delete(entity: LanguageEntity)
}