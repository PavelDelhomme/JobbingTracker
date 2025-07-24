package com.delhomme.jobbingtrack.features.company.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "companies")
@TypeConverters(Converters::class)
data class CompanyEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,

    val name: String,
    val type: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val hrEmail: String? = null,
    val address: String? = null,
    val notes: String? = null,

    // Relations (si nécessaire)
    val applicationIds: List<String> = emptyList(),
    val contactIds: List<String> = emptyList(),
    val followUpIds: List<String> = emptyList(),
    val interviewIds: List<String> = emptyList(),
    val callIds: List<String> = emptyList()
) : BaseEntity()