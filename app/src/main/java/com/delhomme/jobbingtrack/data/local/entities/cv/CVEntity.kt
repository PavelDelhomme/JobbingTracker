package com.delhomme.jobbingtrack.data.local.entities.cv

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "cvs")
data class CVEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val file: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
