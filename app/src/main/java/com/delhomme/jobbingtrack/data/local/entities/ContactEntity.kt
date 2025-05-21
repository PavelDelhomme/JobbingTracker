package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.HasId

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey override val id: String,
    val userId: String,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val entrepriseId: String,
    val candidatureId: String?,
    val notes: String?,
    val syncHash: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long,
) : HasId