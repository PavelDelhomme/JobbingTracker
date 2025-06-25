package com.delhomme.jobbingtrack.companies

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.calls.CallEntity
import com.delhomme.jobbingtrack.commons.entities.CompanyCallCrossRef


data class CompanyWithCalls(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyCallCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)
