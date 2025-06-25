package com.delhomme.jobbingtrack.calls

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.companies.CompanyEntity


data class CallWithCompany(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)
