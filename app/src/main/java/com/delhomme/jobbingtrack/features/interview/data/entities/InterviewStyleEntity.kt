package com.delhomme.jobbingtrack.features.interview.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "interview_styles")
data class InterviewStyleEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
