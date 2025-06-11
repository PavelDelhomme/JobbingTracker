package com.delhomme.jobbingtrack.cvs.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID

@TypeConverters(Converters::class)
@Entity(tableName = "cvs")
data class CVEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val file: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "collaborators")
data class CollaboratorEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val role: String? = null,
    val name: String = "",
    val email: String? = null,
    val phone: String? = null,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

@TypeConverters(Converters::class)
@Entity(tableName = "educations")
data class EducationEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val school: String,
    val degree: String,
    val field: String?,
    val startDate: Long,
    val endDate: Long?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "experiences")
data class ExperienceEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val company: String,
    val description: String?,
    val startDate: Long,
    val endDate: Long?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    val level: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String?,
    val startDate: Long?,
    val endDate: Long?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider


@TypeConverters(Converters::class)
@Entity(tableName = "skills")
data class SkillEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
