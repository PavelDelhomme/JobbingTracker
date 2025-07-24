package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "department_types")
@TypeConverters(Converters::class)
data class DepartmentTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val name: String,
    @ColumnInfo(name = "companyId") val companyId: String? = null
) : BaseEntity()