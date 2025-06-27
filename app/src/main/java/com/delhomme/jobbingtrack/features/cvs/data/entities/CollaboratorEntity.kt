package com.delhomme.jobbingtrack.features.cvs.data.entities

package com.delhomme.jobbingtrack.cvs.entities

import androidx.room.Embedded
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