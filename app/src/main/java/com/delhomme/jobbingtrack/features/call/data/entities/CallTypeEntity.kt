package com.delhomme.jobbingtrack.features.call.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "call_types")
data class CallTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
