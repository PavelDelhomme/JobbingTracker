package com.delhomme.jobbingtrack.features.application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@TypeConverters(Converters::class)
@Entity(tableName = "contract_types")
data class ContractTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val label: String,
    override var userId: String
) : BaseEntity()