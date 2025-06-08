package com.delhomme.jobbingtrack.cv.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String?,
    val startDate: Long?,
    val endDate: Long?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
