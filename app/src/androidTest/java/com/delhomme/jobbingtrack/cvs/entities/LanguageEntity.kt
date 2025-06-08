package com.delhomme.jobbingtrack.cvs.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    val level: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
