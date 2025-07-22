package com.delhomme.jobbingtrack.features.call.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "calls")
@TypeConverters(Converters::class)
data class CallEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,

    val subject: String,
    val companyId: String,
    val contactId: String? = null,
    val applicationId: String? = null,
    val followUpId: String? = null,
    val dateTime: Long,
    val notes: String? = null,

    // TypeID pour lier à CallTypeEntity
    val typeId: String? = null
) : BaseEntity() {
    // Constructeur secondaire pour la migration depuis l'ancien format
    constructor(
        id: String = UUID.randomUUID().toString(),
        subject: String,
        companyId: String,
        contactId: String?,
        applicationId: String?,
        followUpId: String?,
        dateTime: Long,
        notes: String?,
        base: CommonEntityFields
    ) : this(
        id = id,
        userId = base.userId,
        subject = subject,
        companyId = companyId,
        contactId = contactId,
        applicationId = applicationId,
        followUpId = followUpId,
        dateTime = dateTime,
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
        this.entityHash = base.syncHash
    }
}