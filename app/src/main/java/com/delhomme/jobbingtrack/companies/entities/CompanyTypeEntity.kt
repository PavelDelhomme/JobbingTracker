package com.delhomme.jobbingtrack.companies.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "company_types")
data class CompanyTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String
) : HasIdProvider

enum class CompanyTypeStatus {
    UNKNOWN,
    STARTUP,
    PME,
    ETAT,
    ASSOCIATION,
    MULTINATIONALE
}
