package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
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
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var deletedAt: Long? = null,
    var archivedAt: Long? = null
) : HasIdProvider
