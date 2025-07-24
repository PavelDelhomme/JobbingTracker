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
    @ColumnInfo(name = "companyId") val companyId: String,
    @ColumnInfo(name = "contactId") val contactId: String? = null,
    @ColumnInfo(name = "applicationId") val applicationId: String? = null,
    @ColumnInfo(name = "followUpId") val followUpId: String? = null,
    val timestamp: Long,
    val notes: String? = null,

    // TypeID pour lier à CallTypeEntity
    @ColumnInfo(name = "typeId") val typeId: String? = null
) : BaseEntity()