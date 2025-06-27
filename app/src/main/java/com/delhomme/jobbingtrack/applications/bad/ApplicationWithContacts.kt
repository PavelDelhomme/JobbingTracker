package com.delhomme.jobbingtrack.applications.bad

/*
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
*/