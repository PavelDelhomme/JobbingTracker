package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.classes.Entreprise
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import java.util.UUID

fun createEntreprise(name: String): Entreprise {
    val new = Entreprise(
        id = UUID.randomUUID().toString(),
        name = name,
        type = null,
        phone = null,
        email = null,
        hrEmail = null,
        address = null,
        notes = "",
        isArchived = false,
        isDeleted = false,
        syncHash = "ent-${UUID.randomUUID()}"
    )
    FakeDataProvider.entreprises.add(new)
    return new
}
