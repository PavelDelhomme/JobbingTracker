package com.delhomme.jobbingtrack.features.followup.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import java.util.UUID

@Entity(tableName = "followups")
@TypeConverters(Converters::class)
data class FollowUpEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,

    val date: Long,
    val notes: String?,
    val applicationId: String,
    val companyId: String,
    val platformId: String? = null,
    val typeId: String? = null,
    val responseId: String? = null,
    val statusId: String? = null
) : BaseEntity() {
    // Constructeur secondaire pour la migration depuis l'ancien format
    constructor(
        id: String = UUID.randomUUID().toString(),
        date: Long,
        notes: String?,
        applicationId: String,
        companyId: String,
        platformId: String?,
        typeId: String?,
        responseId: String?,
        statusId: String?,
        base: CommonEntityFields
    ) : this(
        id = id,
        userId = base.userId,
        date = date,
        notes = notes,
        applicationId = applicationId,
        companyId = companyId,
        platformId = platformId,
        typeId = typeId,
        responseId = responseId,
        statusId = statusId
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

// Les entités liées restent les mêmes, mais héritent de BaseEntity
@Entity(tableName = "follow_up_platforms")
@TypeConverters(Converters::class)
data class FollowUpPlateformEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "follow_up_status")
@TypeConverters(Converters::class)
data class FollowUpStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "follow_up_types")
@TypeConverters(Converters::class)
data class FollowUpTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val label: String
) : BaseEntity()




@TypeConverters(Converters::class)
@Entity(
    primaryKeys = ["followUpId", "contactId"],
    indices = [ Index("contactId") ]
)
data class FollowUpContactCrossRef(
    val followUpId: String,
    val contactId: String
)




@TypeConverters(Converters::class)
@Entity(
    primaryKeys = ["followUpId", "contactId"],
    indices = [ Index("contactId") ]
)
data class FollowUpContactCrossRef(
    val followUpId: String,
    val contactId: String
)



@TypeConverters(Converters::class)
@Entity(tableName = "follow_up_platforms")
data class FollowUpPlateformEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider



@TypeConverters(Converters::class)
@Entity(tableName = "follow_up_status")
data class FollowUpStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "follow_up_types")
data class FollowUpTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider




data class FollowUpWithContacts(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpContactCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)
