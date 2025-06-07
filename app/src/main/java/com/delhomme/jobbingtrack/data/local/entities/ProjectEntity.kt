package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val title: String,
    val description: String?,
    val startDate: Long?,
    val endDate: Long?,
    val syncHash: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val deletedAt: Long? = null,
    val archivedAt: Long? = null,
) : HasIdProvider
