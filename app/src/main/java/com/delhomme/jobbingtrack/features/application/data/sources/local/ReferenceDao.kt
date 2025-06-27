package com.delhomme.jobbingtrack.features.application.data.sources.local

import androidx.room.Dao
import androidx.room.Query
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationReferenceEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ReferenceType

@Dao
interface ReferenceDao {
    @Query("SELECT * FROM application_references WHERE type = :type")
    fun getByType(type: ReferenceType): List<ApplicationReferenceEntity>
}