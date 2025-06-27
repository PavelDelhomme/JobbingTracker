package com.delhomme.jobbingtrack.features.calendar.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID




@TypeConverters(Converters::class)
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
