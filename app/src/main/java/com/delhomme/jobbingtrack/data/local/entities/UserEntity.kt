package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val job: String,
    val phoneNumber: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val lastLogin: Long?,
    val profilePicture: String?,

    val subject: String,
    val notes: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var deletedAt: Long? = null,
    var archivedAt: Long? = null
) : HasIdProvider