package com.delhomme.jobbingtrack.companies.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    var type: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val hrEmail: String? = null,
    val address: String? = null,
    val notes: String? = null,
    val contactsIds: List<String>?,
    val followUpsIds: List<String>?,
    val applicationsIds: List<String>?,
    val interviewsIds: List<String>?,
    val callsIds: List<String>?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider