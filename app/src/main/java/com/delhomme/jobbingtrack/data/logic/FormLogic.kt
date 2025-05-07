package com.delhomme.jobbingtrack.data.logic


import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.utils.parseDateToMillis
import com.delhomme.jobbingtrack.utils.safeEnumValueOf
import java.util.UUID

fun saveCandidatureFromForm(data: Map<String, String>) {
    val existingId = data["id"];

    val companyName = data["companyName"]?.trim().orEmpty()
    var entreprise = FakeDataProvider.entreprises.find { it.name.equals(companyName, ignoreCase = true) }

    if (entreprise == null) {
        entreprise = Entreprise(
            id = UUID.randomUUID().toString(),
            name = companyName,
            type = null,
            phone = null,
            email = null,
            hrEmail = null,
            address = null,
            syncHash = "ent-${UUID.randomUUID()}"
        )
        FakeDataProvider.entreprises.add(entreprise)
    }

    val existing = existingId?.let { FakeDataProvider.candidatures.find { c -> c.id == it } }

    if (existing != null) {
        existing.title = data["title"] ?: existing.title
        existing.companyName = entreprise.name
        existing.companyId = entreprise.id
        existing.applicationDate = parseDateToMillis(data["applicationDate"])
        existing.location = data["location"]
        existing.platform = data["platform"]
        existing.contractType = data["contractType"]
        existing.notes = data["notes"]
        existing.applicationType = if (data["isSpontaneous"]?.toBoolean() == true) ApplicationType.SPONTANEOUS else ApplicationType.OFFER
        return
    }

    val candidature = Candidature(
        id = UUID.randomUUID().toString(),
        title = data["title"] ?: "Candidature",
        companyName = entreprise.name,
        companyId = entreprise.id,
        applicationDate = parseDateToMillis(data["applicationDate"]),
        location = data["location"],
        platform = data["platform"],
        contractType = data["contractType"],
        notes = data["notes"],
        applicationType = if (data["isSpontaneous"]?.toBoolean() == true) ApplicationType.SPONTANEOUS else ApplicationType.OFFER,
        applicationStatus = ApplicationStatus.WAITING,
        syncHash = "cand-${UUID.randomUUID()}"
    )

    FakeDataProvider.candidatures.add(candidature)
    FakeDataProvider.evenements.add(EventFactory.fromCandidature(candidature))
}

fun saveContactFromForm(data: Map<String, String>) {
    val companyName = data["companyName"]?.trim().orEmpty()
    var entreprise = FakeDataProvider.entreprises.find { it.name.equals(companyName, ignoreCase = true) }

    if (entreprise == null) {
        entreprise = Entreprise(
            id = UUID.randomUUID().toString(),
            name = companyName,
            type = null,
            phone = null,
            email = null,
            hrEmail = null,
            address = null,
            syncHash = "ent-${UUID.randomUUID()}"
        )
        FakeDataProvider.entreprises.add(entreprise)
    }

    val contact = Contact(
        id = UUID.randomUUID().toString(),
        firstName = data["firstName"].orEmpty(),
        lastName = data["lastName"].orEmpty(),
        phone = data["phone"],
        email = data["email"],
        position = data["position"],
        department = data["department"],
        entrepriseId = entreprise.id,
        notes = data["notes"].toString(),
        syncHash = "contact-${UUID.randomUUID()}"
    )

    FakeDataProvider.contacts.add(contact)
}

fun saveRelanceFromForm(data: Map<String, String>) {
    val candidatureId = data["candidatureId"]?.trim().orEmpty()
    val companyId = data["companyId"]?.trim().orEmpty()
    val contactId = data["contactId"]?.trim()?.takeIf { it.isNotBlank() }

    val candidature = FakeDataProvider.candidatures.find { it.id == candidatureId }
        ?: return // Ou créer une nouvelle candidature si nécessaire

    val entreprise = FakeDataProvider.entreprises.find { it.id == companyId }
        ?: return // Pareil ici, on peut aussi créer une nouvelle si besoin

    val relance = Relance(
        id = UUID.randomUUID().toString(),
        date = parseDateToMillis(data["date"]),
        candidatureId = candidature.id,
        companyId = entreprise.id,
        contactId = contactId,
        type = safeEnumValueOf<RelanceType>(data["type"]),
        responseStatus = safeEnumValueOf<RelanceStatus>(data["responseStatus"]),
        notes = data["notes"],
        syncHash = "relance-${UUID.randomUUID()}"
    )

    FakeDataProvider.relances.add(relance)

    // Créer l'événement correspondant
    val evenement = Evenement(
        id = relance.id,
        relatedObjectId = relance.id,
        title = "Relance",
        description = relance.notes,
        startDate = relance.date,
        endDate = relance.date,
        syncHash = "evt-relance-${UUID.randomUUID()}",
        type = "Relances"
    )

    FakeDataProvider.evenements.add(evenement)
}

fun saveAppelFromForm(data: Map<String, String>) {
    val companyId = data["companyId"]?.trim().orEmpty()
    val candidatureId = data["candidatureId"]?.trim()?.takeIf { it.isNotBlank() }
    val contactId = data["contactId"]?.trim()?.takeIf { it.isNotBlank() }
    val relanceId = data["relanceId"]?.trim()?.takeIf { it.isNotBlank() }

    val entreprise = FakeDataProvider.entreprises.find { it.id == companyId }
        ?: return // Ou crée une entreprise ici si tu le souhaites

    val appel = Appel(
        id = UUID.randomUUID().toString(),
        subject = data["subject"] ?: "Appel",
        companyId = entreprise.id,
        candidatureId = candidatureId,
        contactId = contactId,
        relanceId = relanceId,
        dateTime = parseDateToMillis(data["dateTime"]),
        notes = data["notes"],
        syncHash = "appel-${UUID.randomUUID()}"
    )

    FakeDataProvider.appels.add(appel)

    val evenement = Evenement(
        id = appel.id,
        relatedObjectId = appel.id,
        title = appel.subject,
        description = appel.notes,
        startDate = appel.dateTime,
        endDate = appel.dateTime,
        syncHash = "evt-appel-${UUID.randomUUID()}",
        type = "Appels"
    )

    FakeDataProvider.evenements.add(evenement)
}


fun saveEntretienFromForm(data: Map<String, String>) {
    val candidatureId = data["candidatureId"]?.trim().orEmpty()
    val companyId = data["companyId"]?.trim().orEmpty()
    val contacts = data["contacts"]?.split(",")?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList()

    val entretien = Entretien(
        id = UUID.randomUUID().toString(),
        candidatureId = candidatureId,
        companyId = companyId,
        dateTime = parseDateToMillis(data["dateTime"]),
        durationMinutes = data["durationMinutes"]?.toIntOrNull(),
        location = data["location"],
        contacts = contacts,
        style = safeEnumValueOf<EntretienStyle>(data["style"]),
        type = safeEnumValueOf<EntretienType>(data["type"]),
        preInterviewNotes = data["preInterviewNotes"],
        interviewNotes = data["interviewNotes"],
        postInterviewNotes = data["postInterviewNotes"],
        returnDate = data["returnDate"]?.let { parseDateToMillis(it) },
        testsNeeded = data["testsNeeded"]?.toBooleanStrictOrNull() ?: false,
        testsDeadline = data["testsDeadline"]?.let { parseDateToMillis(it) },
        syncHash = "ent-${UUID.randomUUID()}"
    )

    FakeDataProvider.entretiens.add(entretien)

    val durationMillis = (entretien.durationMinutes ?: 30) * 60 * 1000L

    val event = Evenement(
        id = entretien.id,
        relatedObjectId = entretien.id,
        title = "Entretien",
        description = entretien.preInterviewNotes,
        startDate = entretien.dateTime,
        endDate = entretien.dateTime + durationMillis,
        //endDate = entretien.dateTime + (entretien.durationMinutes?.times(60)?.times(1000) ?: 30 * 60 * 1000),
        syncHash = "evt-entretien-${UUID.randomUUID()}",
        type = "Entretiens"
    )

    FakeDataProvider.evenements.add(event)
}


fun saveEntrepriseFromForm(data: Map<String, String>) {
    val entrepriseId = data["id"]?.trim()
    val name = data["name"]?.trim().orEmpty()

    val existing = entrepriseId?.let {
        FakeDataProvider.entreprises.find { e -> e.id == it }
    }

    if (existing != null) {
        existing.name = name
        existing.type = data["type"]
        existing.phone = data["phone"]
        existing.email = data["email"]
        existing.hrEmail = data["hrEmail"]
        existing.address = data["address"]
    } else {
        val newEntreprise = Entreprise(
            id = UUID.randomUUID().toString(),
            name = name,
            type = data["type"],
            phone = data["phone"],
            email = data["email"],
            hrEmail = data["hrEmail"],
            address = data["address"],
            syncHash = "ent-${UUID.randomUUID()}"
        )
        FakeDataProvider.entreprises.add(newEntreprise)
    }
}



