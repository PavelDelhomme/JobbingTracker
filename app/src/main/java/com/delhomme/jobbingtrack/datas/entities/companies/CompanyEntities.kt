package com.delhomme.jobbingtrack.datas.entities.companies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
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



@Entity(tableName = "company_types")
@TypeConverters(Converters::class)
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