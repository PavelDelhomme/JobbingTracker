package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "event_types")
data class EventTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val label: String,
    val synchHash: String,
    val isArchived: Boolean,
    val isDeleted: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val deletedAt: Long? = null,
    val archivedAt: Long? = null,
) : HasIdProvider
