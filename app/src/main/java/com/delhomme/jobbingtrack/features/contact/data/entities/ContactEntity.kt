package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "contacts")
@TypeConverters(Converters::class)
data class ContactEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,

    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val positionId: String?,
    val departmentId: String?,
    val companyId: String,
    val notes: String?
) : BaseEntity() {
    // Constructeur secondaire pour la migration depuis l'ancien format
    constructor(
        id: String = UUID.randomUUID().toString(),
        firstName: String?,
        lastName: String?,
        phone: String?,
        email: String?,
        positionId: String?,
        departmentId: String?,
        companyId: String,
        notes: String?,
        base: CommonEntityFields
    ) : this(
        id = id,
        userId = base.userId,
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        email = email,
        positionId = positionId,
        departmentId = departmentId,
        companyId = companyId,
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
        this.entityHash = base.entityHash
    }
}