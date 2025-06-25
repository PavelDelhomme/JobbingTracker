package com.delhomme.jobbingtrack.companies

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.applications.ApplicationEntity
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef


data class CompanyWithApplications(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyApplicationCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>
)