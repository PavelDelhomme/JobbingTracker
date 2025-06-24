package com.delhomme.jobbingtrack.contacts

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "department_types")
data class DepartmentTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    val companyId: String // ✅ SANS @Embedded
) : HasIdProvider
