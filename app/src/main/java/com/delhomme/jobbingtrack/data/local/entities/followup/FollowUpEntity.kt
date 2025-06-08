package com.delhomme.jobbingtrack.data.local.entities.followup

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "followups")
data class FollowUpEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val date: Long,
    val type: String?,
    val responseStatus: String?,
    val notes: String?,
    val applicationId: String,
    val companyId: String,
    val contactId: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
