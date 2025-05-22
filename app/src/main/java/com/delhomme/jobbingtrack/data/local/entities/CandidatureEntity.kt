package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "candidatures")
data class CandidatureEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val userId: String,
    val companyId: String,
    val applicationDate: Long,
    val platform: String?,
    val contractType: String?,
    val location: String?,
    val applicationType: String,
    val applicationStatus: String,
    val isArchived: Boolean = false,
    val notes: String?,
    val syncHash: String,
    val isDeleted: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var deletedAt: Long? = null,
    var archivedAt: Long? = null
) : HasIdProvider