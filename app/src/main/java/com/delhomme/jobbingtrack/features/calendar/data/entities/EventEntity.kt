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
    @ColumnInfo(name = "user_id") override var userId: String,

    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "start_date") val startDate: Long,
    @ColumnInfo(name = "end_date") val endDate: Long,
    @ColumnInfo(name = "all_day") val allDay: Boolean = false,
    val location: String? = null,
    @ColumnInfo(name = "type_id") val typeId: String? = null,

    // Relations avec d'autres entités
    @ColumnInfo(name = "application_id") val applicationId: String? = null,
    @ColumnInfo(name = "company_id") val companyId: String? = null,
    @ColumnInfo(name = "contact_id") val contactId: String? = null,
    @ColumnInfo(name = "follow_up_id") val followUpId: String? = null,
    @ColumnInfo(name = "interview_id") val interviewId: String? = null,

    // Champs de notification/rappel
    @ColumnInfo(name = "reminder_minutes") val reminderMinutes: Int? = null,
    @ColumnInfo(name = "reminder_sent") val reminderSent: Boolean = false
) : BaseEntity()