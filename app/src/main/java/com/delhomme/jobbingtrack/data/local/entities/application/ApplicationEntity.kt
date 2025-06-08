package com.delhomme.jobbingtrack.data.local.entities.application

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val companyId: String,
    val applicationDate: Long,
    val platform: String?,
    val contractType: String?,
    val location: String?,
    val applicationType: String,
    val applicationStatus: String,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider