package com.delhomme.jobbingtrack.companies

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID


@Entity(tableName = "company_types")
@TypeConverters(Converters::class)
data class CompanyTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String
) : HasIdProvider
