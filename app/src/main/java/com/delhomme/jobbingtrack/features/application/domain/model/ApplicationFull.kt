package com.delhomme.jobbingtrack.etc.applications.bad


/*
data class ApplicationFull(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "application_id",
            entityColumn = "contact_id"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationCallCrossRef::class,
            parentColumn = "application_id",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationFollowUpCrossRef::class,
            parentColumn = "application_id",
            entityColumn = "follow_up_id"
        )
    )
    val followUps: List<FollowUpEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationInterviewCrossRef::class,
            parentColumn = "application_id",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)
*/
