package com.delhomme.jobbingtrack.features.company.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
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


data class CompanyFull(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyContactCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyApplicationCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>,
    // Ajoute ici d'autres relations si besoin
)
