package com.delhomme.jobbingtrack.companies

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.commons.entities.CompanyInterviewCrossRef
import com.delhomme.jobbingtrack.interviews.InterviewEntity


data class CompanyWithInterviews(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyInterviewCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

