package com.delhomme.jobbingtrack.cvs

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID


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
