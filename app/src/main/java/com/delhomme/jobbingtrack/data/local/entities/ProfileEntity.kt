package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.HasId

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey override val id: String,
    val userId: String,
    val subject: String,
    val companyIds: String,
    val contactIds: String?,
    val candidatureIds: String?,
    val relanceIds: String?,
    val notes: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId