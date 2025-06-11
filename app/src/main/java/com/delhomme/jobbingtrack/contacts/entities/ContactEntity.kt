package com.delhomme.jobbingtrack.contacts.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val companyId: String,
    @TypeConverters(Converters::class)
    val applicationIds: List<String>?,
    @TypeConverters(Converters::class)
    val interviewIds: List<String>?,
    @TypeConverters(Converters::class)
    val followUpIds: List<String>?,
    @TypeConverters(Converters::class)
    val callIds: List<String?>,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider