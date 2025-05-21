package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.HasId

@Entity(tableName = "companies")
data class EntrepriseEntity(
    @PrimaryKey override val id: String,
    val name: String,
    val userId: String,
    val type: String?,
    val phone: String?,
    val email: String?,
    val hrEmail: String?,
    val address: String?,
    val notes: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId