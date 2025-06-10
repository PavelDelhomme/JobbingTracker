package com.delhomme.jobbingtrack.cvs.entities

import androidx.room.Embedded
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "collaborators")
data class CollaboratorEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val role: String? = null,
    val name: String = "",
    val email: String? = null,
    val phone: String? = null,
    @Embedded val base: CommonEntityFields
) : HasIdProvider