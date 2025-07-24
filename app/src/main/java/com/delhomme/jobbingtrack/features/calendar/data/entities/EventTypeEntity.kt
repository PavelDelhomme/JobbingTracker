package com.delhomme.jobbingtrack.features.calendar.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "event_types")
@TypeConverters(Converters::class)
data class EventTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val label: String,
    val color: String? = null
) : BaseEntity()