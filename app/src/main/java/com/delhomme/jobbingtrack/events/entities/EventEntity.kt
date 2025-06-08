package com.delhomme.jobbingtrack.events.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val relatedObjectId: String?,
    val title: String,
    val description: String?,
    val startDate: Long?,
    val endDate: Long?,
    val type: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
