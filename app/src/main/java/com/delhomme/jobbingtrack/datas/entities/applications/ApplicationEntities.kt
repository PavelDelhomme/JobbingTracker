package com.delhomme.jobbingtrack.datas.entities.applications

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
    val platformId: String?,
    val contractTypeId: String?,
    val location: String?,
    val applicationTypeId: String,
    val applicationStatusId: String,
    val notes: String?,
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
