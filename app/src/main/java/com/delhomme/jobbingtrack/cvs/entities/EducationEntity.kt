package com.delhomme.jobbingtrack.cvs.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "educations")
data class EducationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val school: String,
    val degree: String,
    val field: String?,
    val startDate: Long,
    val endDate: Long?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider