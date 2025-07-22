package com.delhomme.jobbingtrack.features.company.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "companies")
@TypeConverters(Converters::class)
data class CompanyEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,

    val name: String,
    val type: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val hrEmail: String? = null,
    val address: String? = null,
    val notes: String? = null,

    // Relations (si nécessaire)
    val applicationIds: List<String> = emptyList(),
    val contactIds: List<String> = emptyList(),
    val followUpIds: List<String> = emptyList(),
    val interviewIds: List<String> = emptyList(),
    val callIds: List<String> = emptyList()
) : BaseEntity() {
    // Constructeur secondaire pour la migration depuis l'ancien format
    constructor(
        id: String = UUID.randomUUID().toString(),
        name: String,
        type: String?,
        phone: String?,
        email: String?,
        hrEmail: String?,
        address: String?,
        notes: String?,
        base: CommonEntityFields
    ) : this(
        id = id,
        userId = base.userId,
        name = name,
        type = type,
        phone = phone,
        email = email,
        hrEmail = hrEmail,
        address = address,
        notes = notes
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