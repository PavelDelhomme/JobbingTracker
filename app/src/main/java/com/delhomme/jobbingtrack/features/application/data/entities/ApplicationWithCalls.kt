package com.delhomme.jobbingtrack.applications.bad

/*
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
 */