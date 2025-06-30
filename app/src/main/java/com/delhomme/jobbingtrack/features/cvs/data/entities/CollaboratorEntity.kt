package com.delhomme.jobbingtrack.features.cvs.data.entities


import androidx.room.Embedded
import java.util.UUID
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider


@Entity(tableName = "collaborators")
data class CollaboratorEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val cvId: String,
    val projectId: String,
    val role: String? = null,
    val name: String = "",
    val email: String? = null,
    val phone: String? = null,
    @Embedded val base: CommonEntityFields
) : HasIdProvider