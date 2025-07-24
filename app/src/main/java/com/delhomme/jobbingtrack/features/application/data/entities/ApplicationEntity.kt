package com.delhomme.jobbingtrack.features.application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "applications")
@TypeConverters(Converters::class)
data class ApplicationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override var userId: String,

    // Champs spécifiques à l'application
    val title: String,
    val companyId: String,
    val applicationDate: Long,

    // Relations simplifiées (listes d'IDs)
    val contactIds: List<String> = emptyList(),
    val callIds: List<String> = emptyList(),
    val interviewIds: List<String> = emptyList(),
    val followUpIds: List<String> = emptyList(),

    // Références unifiées
    val platformRefId: String? = null,
    val contractTypeRefId: String? = null,
    val typeRefId: String? = null,
    val statusRefId: String? = null,

    // Autres champs
    val location: String? = null,
    val notes: String? = null
) : BaseEntity() {
    // Constructeur secondaire pour la migration depuis l'ancien format
    constructor(
        id: String = UUID.randomUUID().toString(),
        title: String,
        companyId: String,
        applicationDate: Long,
        applicationType: String? = null,
        applicationStatus: String? = null,
        base: CommonEntityFields
    ) : this(
        id = id,
        userId = base.userId,
        title = title,
        companyId = companyId,
        applicationDate = applicationDate,
        typeRefId = applicationType,
        statusRefId = applicationStatus
    ) {
        // Copier les métadonnées depuis CommonEntityFields
        this.createdAt = base.createdAt
        this.updatedAt = base.updatedAt
        this.isDeleted = base.isDeleted
        this.isArchived = base.isArchived
        this.deletedAt = base.deletedAt
        this.archivedAt = base.archivedAt
        this.syncHash = base.syncHash
        this.entityHash = base.syncHash // Utiliser syncHash comme entityHash initial
    }
}