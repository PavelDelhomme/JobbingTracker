package com.delhomme.jobbingtrack.features.call.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "calls")
@TypeConverters(Converters::class)
data class CallEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,

    val subject: String,
    @ColumnInfo(name = "company_id") val companyId: String,
    @ColumnInfo(name = "contact_id") val contactId: String? = null,
    @ColumnInfo(name = "application_id") val applicationId: String? = null,
    @ColumnInfo(name = "follow_up_id") val followUpId: String? = null,
    val timestamp: Long,
    val notes: String? = null,

    // TypeID pour lier à CallTypeEntity
    @ColumnInfo(name = "type_id") val typeId: String? = null
) : BaseEntity()