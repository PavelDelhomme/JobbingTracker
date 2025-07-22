package com.delhomme.jobbingtrack.features.user.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val email: String,
    val firstName: String = "",
    val lastName: String = "",
    val isActive: Boolean = true,
    val dateJoined: Long = System.currentTimeMillis(),

    // Nous ne stockons pas le mot de passe en clair en local
    // Ces champs sont pour la gestion de la session
    @Ignore val accessToken: String? = null,
    @Ignore val refreshToken: String? = null,

    // Champs communs pour la synchronisation
    val syncHash: String = "",
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var deletedAt: Long? = null,
    var archivedAt: Long? = null
) : HasIdProvider