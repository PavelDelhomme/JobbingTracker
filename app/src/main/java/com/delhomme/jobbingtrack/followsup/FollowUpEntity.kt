package com.delhomme.jobbingtrack.followsup

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
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