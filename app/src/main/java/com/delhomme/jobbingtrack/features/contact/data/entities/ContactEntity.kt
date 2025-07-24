package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "contacts")
@TypeConverters(Converters::class)
data class ContactEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,

    val firstName: String,
    val lastName: String,
    val email: String? = null,
    val phone: String? = null,
    val mobile: String? = null,
    val title: String? = null,
    @ColumnInfo(name = "companyId") val companyId: String? = null,
    @ColumnInfo(name = "departmentTypeId") val departmentTypeId: String? = null,
    @ColumnInfo(name = "positionTypeId") val positionTypeId: String? = null,
    val notes: String? = null,
    val linkedinUrl: String? = null,
    val photoUrl: String? = null,

    // Relation vers d'autres entités (si nécessaire)
    val applicationIds: List<String> = emptyList(),
    val followUpIds: List<String> = emptyList(),
    val callIds: List<String> = emptyList(),
    val interviewIds: List<String> = emptyList()
) : BaseEntity()