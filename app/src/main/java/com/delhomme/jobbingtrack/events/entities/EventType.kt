package com.delhomme.jobbingtrack.events.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "event_types")
data class EventTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
