package com.delhomme.jobbingtrack.companies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.Converters
import java.util.UUID


@Entity(tableName = "companies")
@TypeConverters(Converters::class)
data class CompanyEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val name: String,
    var type: String?, // FK vers CompanyTypeEntity
    val phone: String? = null,
    val email: String? = null,
    val hrEmail: String? = null,
    val address: String? = null,
    val notes: String? = null,
    //val applicationsIds: List<String>?,
    //val contactsIds: List<String>?,
    //val followUpsIds: List<String>?,
    //val interviewsIds: List<String>?,
    //val callsIds: List<String>?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider

