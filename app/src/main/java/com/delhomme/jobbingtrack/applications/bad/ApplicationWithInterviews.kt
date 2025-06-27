package com.delhomme.jobbingtrack.applications.bad

/*
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
*/