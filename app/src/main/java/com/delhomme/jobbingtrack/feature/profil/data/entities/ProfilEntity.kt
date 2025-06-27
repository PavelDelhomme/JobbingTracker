package com.delhomme.jobbingtrack.feature.profil.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.UUID


@TypeConverters(Converters::class)
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