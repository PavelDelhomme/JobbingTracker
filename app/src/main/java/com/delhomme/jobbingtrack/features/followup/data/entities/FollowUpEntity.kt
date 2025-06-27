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
import com.delhomme.jobbingtrack.core.utils.Converters
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "followups")
data class FollowUpEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val date: Long,
    val notes: String?,
    val applicationId: String,
    val companyId: String,
    //val contactsIds: List<String?>, // ManyToMany
    //val callsIds: List<String?>, // OneToMany
    val platformId: String?, // FK vers FolllowUpPlateformEntity
    val typeId: String?, // FK vers FollowUpTypeEntity
    val responseId: String?, // FK vers FollowUpResponseEntity
    val statusId: String?, // FK vers FollowUpStatusEntity
    @Embedded val base: CommonEntityFields
) : HasIdProvider




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
