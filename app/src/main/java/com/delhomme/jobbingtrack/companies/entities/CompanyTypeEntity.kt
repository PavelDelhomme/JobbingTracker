package com.delhomme.jobbingtrack.companies.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "company_types")
data class CompanyTypeEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String
)

enum class CompanyTypeStatus {
    UNKNOWN,
    STARTUP,
    PME,
    ETAT,
    ASSOCIATION,
    MULTINATIONALE
}
