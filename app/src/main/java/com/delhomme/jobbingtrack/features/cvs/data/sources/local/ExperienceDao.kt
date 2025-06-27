package com.delhomme.jobbingtrack.features.cvs.data.sources.local


@Dao
interface ExperienceDao {
    @Query("SELECT * FROM experiences WHERE isDeleted = 0")
    fun getAll(): Flow<List<ExperienceEntity>>

    @Query("SELECT * FROM experiences WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ExperienceEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ExperienceEntity)

    @Update
    suspend fun update(entity: ExperienceEntity)

    @Delete
    suspend fun delete(entity: ExperienceEntity)
}