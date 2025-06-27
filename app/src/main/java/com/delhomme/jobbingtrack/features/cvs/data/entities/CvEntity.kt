package com.delhomme.jobbingtrack.features.cvs.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import java.util.UUID

@Entity(tableName = "cvs")
data class CVEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val file: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider