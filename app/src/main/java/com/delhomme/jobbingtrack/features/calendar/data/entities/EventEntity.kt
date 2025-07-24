package com.delhomme.jobbingtrack.features.calendar.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "events")
@TypeConverters(Converters::class)
data class EventEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,

    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "startDate") val startDate: Long,
    @ColumnInfo(name = "endDate") val endDate: Long,
    @ColumnInfo(name = "allDay") val allDay: Boolean = false,
    val location: String? = null,
    @ColumnInfo(name = "typeId") val typeId: String? = null,

    // Relations avec d'autres entités
    @ColumnInfo(name = "applicationId") val applicationId: String? = null,
    @ColumnInfo(name = "companyId") val companyId: String? = null,
    @ColumnInfo(name = "contactId") val contactId: String? = null,
    @ColumnInfo(name = "followUpId") val followUpId: String? = null,
    @ColumnInfo(name = "interviewId") val interviewId: String? = null,

    // Champs de notification/rappel
    @ColumnInfo(name = "reminderMinutes") val reminderMinutes: Int? = null,
    @ColumnInfo(name = "reminderSent") val reminderSent: Boolean = false
) : BaseEntity()