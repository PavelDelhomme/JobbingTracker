package com.delhomme.jobbingtrack.core.database

import androidx.room.Embedded
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID


abstract class BaseEntity(
    val id: String = UUID.randomUUID().toString(),
    @Embedded
    val base: CommonEntityFields
) : HasIdProvider