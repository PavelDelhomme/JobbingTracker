package com.delhomme.jobbingtrack.data.local.entities.cv

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "profiles")
data class ProfilEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val subject: String,
    val companyIds: String,
    val contactIds: String?,
    val applicationIds: String?,
    val followUpIds: String?,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider