package com.delhomme.jobbingtrack.applications.bad

/*
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
*/