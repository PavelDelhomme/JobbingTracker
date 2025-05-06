package com.delhomme.jobbingtrack.data.logic


import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

fun saveCandidatureFromForm(data: Map<String, String>) {
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

fun parseDateToMillis(input: String?): Long {
    return try {
        LocalDate.parse(input?.trim())
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    } catch (e: Exception) {
        System.currentTimeMillis()
    }
}