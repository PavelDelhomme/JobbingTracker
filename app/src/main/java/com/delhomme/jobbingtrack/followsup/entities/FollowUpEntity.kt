package com.delhomme.jobbingtrack.followsup.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
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
    val contactsIds: List<String?>,
    val callsIds: List<String?>,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
