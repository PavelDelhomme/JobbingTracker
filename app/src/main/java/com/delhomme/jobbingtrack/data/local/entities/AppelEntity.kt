package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.HasId

@Entity(tableName = "appels")
data class AppelEntity(
    @PrimaryKey override val id: String,
    val userId: String,
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val candidatureId: String?,
    val relanceId: String?,
    val dateTime: Long,
    val notes: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
) : HasId