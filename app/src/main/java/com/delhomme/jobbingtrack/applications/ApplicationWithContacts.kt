package com.delhomme.jobbingtrack.applications

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.calls.CallEntity
import com.delhomme.jobbingtrack.commons.entities.ApplicationCallCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationInterviewCrossRef
import com.delhomme.jobbingtrack.contacts.ContactEntity
import com.delhomme.jobbingtrack.followsup.FollowUpEntity
import com.delhomme.jobbingtrack.interviews.InterviewEntity


data class ApplicationWithContacts(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)


data class ApplicationWithCalls(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationCallCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)
data class ApplicationWithFollowUps(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationFollowUpCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>
)
data class ApplicationWithInterviews(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationInterviewCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

data class ApplicationFull(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationCallCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationFollowUpCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationInterviewCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

