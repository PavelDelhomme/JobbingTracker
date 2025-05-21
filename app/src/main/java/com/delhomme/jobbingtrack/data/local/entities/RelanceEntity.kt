package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.HasId

@Entity(tableName = "relances")
data class RelanceEntity(
    @PrimaryKey override val id: String,
    val userId: String,
    val date: Long,
    val type: String?,
    val responseStatus: String?,
    val notes: String?,
    val candidatureId: String,
    val companyId: String,
    val contactId: String?,
    val syncHash: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId
