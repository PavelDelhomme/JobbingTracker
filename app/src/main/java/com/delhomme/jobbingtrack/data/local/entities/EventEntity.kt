package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.HasId

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey override val id: String,
    val userId: String,
    val relatedObjectId: String?,
    val title: String,
    val description: String?,
    val startDate: Long?,
    val enddate: Long?,
    val syncHash: String,
    val type: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId
