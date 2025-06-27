package com.delhomme.jobbingtrack.features.call.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "calls")
data class CallEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val applicationId: String?,
    val followUpId: String?,
    val dateTime: Long,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider



