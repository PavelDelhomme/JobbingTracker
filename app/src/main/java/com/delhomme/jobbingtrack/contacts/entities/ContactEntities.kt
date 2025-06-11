package com.delhomme.jobbingtrack.contacts.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID

@TypeConverters(Converters::class)
@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val companyId: String,
    val applicationIds: List<String>?,
    val interviewIds: List<String>?,
    val followUpIds: List<String>?,
    val callIds: List<String?>,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "department_types")
data class DepartmentTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    val companyId: String // ✅ SANS @Embedded
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "position_types")
data class PositionTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider