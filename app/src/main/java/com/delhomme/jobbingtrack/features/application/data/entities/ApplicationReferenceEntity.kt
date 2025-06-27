package com.delhomme.jobbingtrack.features.application.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "application_references")
data class ApplicationReferenceEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val type: ReferenceType, // Enum : PLATFORM, STATUS, CONTRACT_TYPE
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

enum class ReferenceType { PLATFORM, STATUS, CONTRACT_TYPE, APPLICATION_TYPE }
