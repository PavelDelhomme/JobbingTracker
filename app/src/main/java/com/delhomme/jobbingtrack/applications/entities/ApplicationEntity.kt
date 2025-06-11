package com.delhomme.jobbingtrack.applications.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID

@TypeConverters(Converters::class)
@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val companyId: String,
    val applicationDate: Long,
    val platform: String?,
    val contractType: String?,
    val location: String?,
    val applicationType: String,
    val applicationStatus: String,
    val notes: String?,
    val followUpsIds: List<String> = emptyList(),
    val contactsIds: List<String> = emptyList(),
    val callsIds: List<String> = emptyList(),
    val interviewsIds: List<String> = emptyList(),
    @Embedded val base: CommonEntityFields
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "application_platforms")
data class ApplicationPlatformEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "application_statuses")
data class ApplicationStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "application_types")
data class ApplicationTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "contract_types")
data class ContractTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider