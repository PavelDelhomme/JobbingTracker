package com.delhomme.jobbingtrack.features.application.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

/*
import androidx.room.Entity
import com.delhomme.jobbingtrack.core.database.BaseEntity

@Entity(tableName = "applications")
data class ApplicationEntity(
    val title: String,
    val companyId: String,
    // ... autres champs spécifiques
) : BaseEntity()
 */


@TypeConverters(Converters::class)
@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val companyId: String,
    val applicationDate: Long,

    // Relations simplifiées (listes d'IDs)
    val contactIds: List<String> = emptyList(),
    val callIds: List<String> = emptyList(),
    val interviewIds: List<String> = emptyList(),
    val followUpIds: List<String> = emptyList(),

    // Références unifiées
    val platformRefId: String?,
    val contractTypeRefId: String?,
    val typeRefId: String,
    val statusRefId: String,

    // Autres champs
    val location: String?,
    val notes: String?,

    @Embedded val base: CommonEntityFields
) : HasIdProvider

