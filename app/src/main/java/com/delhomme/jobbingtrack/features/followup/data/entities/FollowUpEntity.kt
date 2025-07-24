package com.delhomme.jobbingtrack.features.followup.data.entities

import androidx.room.ColumnInfo
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
    @PrimaryKey override var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,

    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "applicationId") val applicationId: String? = null,
    @ColumnInfo(name = "companyId") val companyId: String,
    @ColumnInfo(name = "contactId") val contactId: String? = null,
    val date: Long,
    @ColumnInfo(name = "reminderDate") val reminderDate: Long? = null,
    @ColumnInfo(name = "typeId") val typeId: String? = null,
    @ColumnInfo(name = "platformId") val platformId: String? = null,
    @ColumnInfo(name = "statusId") val statusId: String? = null
) : BaseEntity()

// Les entités liées restent les mêmes, mais héritent de BaseEntity
@Entity(tableName = "follow_up_platforms")
@TypeConverters(Converters::class)
data class FollowUpPlatformEntity(
    @PrimaryKey override var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "follow_up_statuses")
@TypeConverters(Converters::class)
data class FollowUpStatusEntity(
    @PrimaryKey override var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "follow_up_types")
@TypeConverters(Converters::class)
data class FollowUpTypeEntity(
    @PrimaryKey override var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
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
@Entity(tableName = "follow_up_platforms")
data class FollowUpPlateformEntity(
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
