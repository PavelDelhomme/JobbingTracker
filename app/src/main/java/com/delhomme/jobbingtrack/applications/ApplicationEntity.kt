package com.delhomme.jobbingtrack.applications

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val companyId: String,
    val applicationDate: Long,
    val platformId: String?,
    val contractTypeId: String?,
    val location: String?,
    val applicationTypeId: String,
    val applicationStatusId: String,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

