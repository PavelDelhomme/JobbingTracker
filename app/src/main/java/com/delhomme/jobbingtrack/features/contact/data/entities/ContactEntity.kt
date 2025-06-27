package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val positionId: String?,
    val departmentId: String?,
    val companyId: String,
    //val applicationIds: List<String>?,
    //val interviewIds: List<String>?,
    //val followUpIds: List<String>?,
    //val callIds: List<String?>,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
