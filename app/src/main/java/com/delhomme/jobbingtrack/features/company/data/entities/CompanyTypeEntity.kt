package com.delhomme.jobbingtrack.features.company.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "company_types")
@TypeConverters(Converters::class)
data class CompanyTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val name: String
) : BaseEntity()