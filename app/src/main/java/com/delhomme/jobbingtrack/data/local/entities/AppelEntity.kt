package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appels")
data class AppelEntity(
    @PrimaryKey val id: String,
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
)