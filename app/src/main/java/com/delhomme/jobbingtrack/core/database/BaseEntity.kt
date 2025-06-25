package com.delhomme.jobbingtrack.core.database

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID


@MappedSuperclass
abstract class BaseEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    @Embedded
    val base: CommonEntityFields
) : HasIdProvider