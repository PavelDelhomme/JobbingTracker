package com.delhomme.jobbingtrack.data.local.entities.call

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "calls")
data class CallEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val applicationId: String?,
    val followUpId: String?,
    val dateTime: Long,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider