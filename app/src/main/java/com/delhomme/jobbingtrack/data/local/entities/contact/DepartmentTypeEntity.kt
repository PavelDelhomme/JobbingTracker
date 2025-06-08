package com.delhomme.jobbingtrack.data.local.entities.contact

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.company.CompanyEntity
import java.util.UUID

@Entity(tableName = "department_types")
data class DepartmentTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    @Embedded
    val company: CompanyEntity
) : HasIdProvider