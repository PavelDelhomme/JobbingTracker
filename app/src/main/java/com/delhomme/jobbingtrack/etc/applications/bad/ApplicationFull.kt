package com.delhomme.jobbingtrack.applications.bad

/*
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
*/
