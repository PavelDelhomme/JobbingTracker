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
@Entity(tableName = "follow_up_status")
data class FollowUpStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
