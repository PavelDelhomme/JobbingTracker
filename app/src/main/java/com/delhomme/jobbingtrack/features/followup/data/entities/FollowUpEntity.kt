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

@Entity(tableName = "follow_ups")
@TypeConverters(Converters::class)
data class FollowUpEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "user_id") override val userId: String,

    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "application_id") val applicationId: String? = null,
    @ColumnInfo(name = "company_id") val companyId: String,
    @ColumnInfo(name = "contact_id") val contactId: String? = null,
    val date: Long,
    @ColumnInfo(name = "reminder_date") val reminderDate: Long? = null,
    @ColumnInfo(name = "type_id") val typeId: String? = null,
    @ColumnInfo(name = "platform_id") val platformId: String? = null,
    @ColumnInfo(name = "status_id") val statusId: String? = null
) : BaseEntity()

// Les entités liées restent les mêmes, mais héritent de BaseEntity
@Entity(tableName = "follow_up_platforms")
@TypeConverters(Converters::class)
data class FollowUpPlatformEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "user_id") override val userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "follow_up_statuses")
@TypeConverters(Converters::class)
data class FollowUpStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "user_id") override val userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "follow_up_types")
@TypeConverters(Converters::class)
data class FollowUpTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "user_id") override val userId: String,
    val label: String
) : BaseEntity()




@TypeConverters(Converters::class)
@Entity(
    primaryKeys = ["follow_up_id", "contact_id"],
    indices = [ Index("contact_id") ]
)
data class FollowUpContactCrossRef(
    val followUpId: String,
    val contactId: String
)




@TypeConverters(Converters::class)
@Entity(
    primaryKeys = ["follow_up_id", "contact_id"],
    indices = [ Index("contact_id") ]
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
            parentColumn = "follow_up_id",
            entityColumn = "contact_id"
        )
    )
    val contacts: List<ContactEntity>
)
