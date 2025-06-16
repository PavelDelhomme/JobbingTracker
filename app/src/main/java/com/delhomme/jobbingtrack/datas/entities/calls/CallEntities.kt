package com.delhomme.jobbingtrack.datas.entities.calls

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.entities.CallWithContactsCrossRef
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "calls")
data class CallEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val applicationId: String?,
    val followUpId: String?,
    val dateTime: Long,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "call_types")
data class CallTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider


data class CallVithContact(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "contactId",
        entityColumn = "id",
        associateBy = Junction(
            CallWithContactsCrossRef::class,
            parentColumn = "callId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)
