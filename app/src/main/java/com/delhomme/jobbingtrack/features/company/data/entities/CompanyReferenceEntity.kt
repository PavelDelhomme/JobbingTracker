package com.delhomme.jobbingtrack.features.company.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity


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




data class CompanyWithContacts(
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
    val contacts: List<ContactEntity>
)





data class CompanyWithFollowUps(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyFollowUpCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>
)



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

