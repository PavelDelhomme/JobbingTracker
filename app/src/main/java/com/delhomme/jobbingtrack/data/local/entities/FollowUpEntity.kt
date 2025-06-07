package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "followups")
data class FollowUpEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val date: Long,
    val type: String?,
    val responseStatus: String?,
    val notes: String?,
    val applicationId: String,
    val companyId: String,
    val contactId: String?,
    val syncHash: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var deletedAt: Long? = null,
    var archivedAt: Long? = null
) : HasIdProvider
