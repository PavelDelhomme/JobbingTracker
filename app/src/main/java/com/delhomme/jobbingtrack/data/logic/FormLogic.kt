package com.delhomme.jobbingtrack.data.logic


import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.utils.parseDateToMillis
import com.delhomme.jobbingtrack.utils.safeEnumValueOf
import java.util.UUID

fun saveCandidatureFromForm(data: Map<String, String>) {
    val existingId = data["id"]
    val companyName = data["companyName"]?.trim().orEmpty()
    val entreprise = FakeDataProvider.addEntrepriseIfNotExists(companyName)

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
        existing.applicationType = safeEnumValueOf<ApplicationType>(data["applicationType"]) ?: ApplicationType.OFFER
        existing.applicationStatus = safeEnumValueOf<ApplicationStatus>(data["applicationStatus"]) ?: ApplicationStatus.WAITING
        existing.isArchived = data["isArchived"]?.toBooleanStrictOrNull() ?: false
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
        applicationType = safeEnumValueOf<ApplicationType>(data["applicationType"]) ?: ApplicationType.OFFER,
        applicationStatus = safeEnumValueOf<ApplicationStatus>(data["applicationStatus"]) ?: ApplicationStatus.WAITING,
        isArchived = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
        syncHash = "cand-${UUID.randomUUID()}"
    )

    FakeDataProvider.candidatures.add(candidature)
    FakeDataProvider.evenements.add(EventFactory.fromCandidature(candidature))
}

fun saveContactFromForm(data: Map<String, String>) {
    val companyName = data["companyName"]?.trim().orEmpty()
    val entreprise = FakeDataProvider.addEntrepriseIfNotExists(companyName)

    val contact = Contact(
        id = UUID.randomUUID().toString(),
        firstName = data["firstName"].orEmpty(),
        lastName = data["lastName"].orEmpty(),
        phone = data["phone"],
        email = data["email"],
        position = data["position"],
        department = data["department"],
        entrepriseId = entreprise.id,
        notes = data["notes"] ?: "",
        syncHash = "contact-${UUID.randomUUID()}"
    )

    FakeDataProvider.contacts.add(contact)
}
fun saveRelanceFromForm(data: Map<String, String>) {
    val candidatureId = data["candidatureId"]?.trim().orEmpty()
    val companyId = data["companyId"]?.trim().orEmpty()
    val contactId = data["contactId"]?.trim()?.takeIf { it.isNotBlank() }

    val relance = Relance(
        id = UUID.randomUUID().toString(),
        date = parseDateToMillis(data["date"]),
        candidatureId = candidatureId,
        companyId = companyId,
        contactId = contactId,
        type = safeEnumValueOf<RelanceType>(data["type"]),
        responseStatus = safeEnumValueOf<RelanceStatus>(data["responseStatus"]),
        notes = data["notes"],
        syncHash = "relance-${UUID.randomUUID()}"
    )

    FakeDataProvider.relances.add(relance)
    FakeDataProvider.evenements.add(EventFactory.fromRelance(relance))
}
fun saveAppelFromForm(data: Map<String, String>) {
    val companyId = data["companyId"]?.trim().orEmpty()

    val appel = Appel(
        id = UUID.randomUUID().toString(),
        subject = data["subject"] ?: "Appel",
        companyId = companyId,
        candidatureId = data["candidatureId"]?.trim(),
        contactId = data["contactId"]?.trim(),
        relanceId = data["relanceId"]?.trim(),
        dateTime = parseDateToMillis(data["dateTime"]),
        notes = data["notes"],
        syncHash = "appel-${UUID.randomUUID()}"
    )

    FakeDataProvider.appels.add(appel)
    FakeDataProvider.evenements.add(EventFactory.fromAppel(appel))
}

fun saveEntretienFromForm(data: Map<String, String>) {
    val entretien = Entretien(
        id = UUID.randomUUID().toString(),
        candidatureId = data["candidatureId"]?.trim().orEmpty(),
        companyId = data["companyId"]?.trim().orEmpty(),
        dateTime = parseDateToMillis(data["dateTime"]),
        durationMinutes = data["durationMinutes"]?.toIntOrNull(),
        location = data["location"],
        contacts = data["contacts"]?.split(",")?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList(),
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
    FakeDataProvider.evenements.add(EventFactory.fromEntretien(entretien))
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
        existing.notes = data["notes"]
        existing.isArchived = data["isArchived"]?.toBooleanStrictOrNull() ?: false
    } else {
        val newEntreprise = Entreprise(
            id = UUID.randomUUID().toString(),
            name = name,
            type = data["type"],
            phone = data["phone"],
            email = data["email"],
            hrEmail = data["hrEmail"],
            address = data["address"],
            notes = data["notes"],
            syncHash = "ent-${UUID.randomUUID()}",
            isArchived = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted = data["isDeleted"]?.toBooleanStrictOrNull() ?: false
        )
        FakeDataProvider.entreprises.add(newEntreprise)
    }
}


