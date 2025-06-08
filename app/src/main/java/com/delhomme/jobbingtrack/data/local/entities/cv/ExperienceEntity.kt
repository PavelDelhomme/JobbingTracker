package com.delhomme.jobbingtrack.data.local.entities.cv

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "experiences")
data class ExperienceEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val company: String,
    val description: String?,
    val startDate: Long,
    val endDate: Long?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
