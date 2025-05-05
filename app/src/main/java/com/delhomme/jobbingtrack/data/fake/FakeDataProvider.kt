package com.delhomme.jobbingtrack.data.fake

import com.delhomme.jobbingtrack.data.classes.*
import kotlin.random.Random

object FakeDataProvider {

    private fun randomSyncHash(prefix: String) = "$prefix-${Random.nextInt(1000, 9999)}"

    val entreprises = MutableList(10) { index ->
        Entreprise(
            id = (index + 1).toString(),
            name = "Entreprise ${index + 1}",
            type = listOf("Tech", "E-commerce", "Finance", "Santé").random(),
            phone = "01${Random.nextInt(10,99)}${Random.nextInt(10,99)}${Random.nextInt(10,99)}",
            email = "contact${index + 1}@example.com",
            hrEmail = "rh${index + 1}@example.com",
            address = "Adresse ${index + 1} rue Excellente",
            syncHash = randomSyncHash("entreprise")
        )
    }

    val contacts = MutableList(10) { index ->
        Contact(
            id = (index + 1).toString(),
            firstName = "Prénom$index",
            lastName = "Nom$index",
            phone = "06${Random.nextInt(10,99)}${Random.nextInt(10,99)}${Random.nextInt(10,99)}",
            email = "contact${index}@mail.com",
            position = listOf("Manager", "Recruteur", "Chef de projet").random(),
            department = listOf("RH", "Tech", "Marketing").random(),
            entrepriseId = entreprises.random().id,
            notes = "Notes $index",
            syncHash = randomSyncHash("contact")
        )
    }

    val candidatures = MutableList(10) { index ->
        Candidature(
            id = (index + 1).toString(),
            title = "Poste ${listOf("Développeur", "Ingénieur", "Chef de Projet").random()}",
            companyName = entreprises.random().name,
            applicationDate = System.currentTimeMillis() - Random.nextLong(0, 1000000000),
            location = listOf("Paris", "Lyon", "Marseille", "Lille").random(),
            platform = listOf("LinkedIn", "Indeed", "Monster", "Welcome to the Jungle").random(),
            contractType = listOf("CDI", "CDD", "Alternance", "Stage").random(),
            notes = "Note sur la candidature ${index + 1}",
            applicationType = ApplicationType.values().random(),
            applicationStatus = ApplicationStatus.values().random(),
            companyId = entreprises.random().id,
            syncHash = randomSyncHash("candidature")
        )
    }

    val relances = MutableList(10) { index ->
        Relance(
            id = (index + 1).toString(),
            date = System.currentTimeMillis() - Random.nextLong(0, 700000000),
            contactId = contacts.random().id,
            candidatureId = candidatures.random().id,
            companyId = entreprises.random().id,
            responseStatus = RelanceStatus.values().random(),
            type = RelanceType.values().random(),
            notes = "Note sur la relance ${index + 1}",
            syncHash = randomSyncHash("relance")
        )
    }

    val appels = MutableList(10) { index ->
        Appel(
            id = (index + 1).toString(),
            subject = "Appel ${listOf("Suivi", "Relance", "Information").random()} ${index + 1}",
            companyId = entreprises.random().id,
            contactId = contacts.random().id,
            candidatureId = candidatures.random().id,
            dateTime = System.currentTimeMillis() - Random.nextLong(0, 500000000),
            notes = "Notes de l'appel ${index + 1}",
            syncHash = randomSyncHash("appel"),
            relanceId = relances.random().id,
        )
    }

    val entretiens = MutableList(10) { index ->
        Entretien(
            id = (index + 1).toString(),
            candidatureId = candidatures.random().id,
            companyId = entreprises.random().id,
            dateTime = System.currentTimeMillis() + Random.nextLong(0, 1000000000),
            durationMinutes = listOf(30, 45, 60).random(),
            location = listOf("Paris", "Lyon", "Visio").random(),
            contacts = listOf(contacts.random().id),
            style = EntretienStyle.values().random(),
            type = EntretienType.values().random(),
            preInterviewNotes = "Notes de préparation ${index + 1}",
            interviewNotes = "Notes pendant entretien ${index + 1}",
            postInterviewNotes = "Notes post entretien ${index + 1}",
            returnDate = System.currentTimeMillis() + Random.nextLong(100000000, 200000000),
            testsNeeded = Random.nextBoolean(),
            testsDeadline = System.currentTimeMillis() + Random.nextLong(200000000, 300000000),
            syncHash = randomSyncHash("entretien")
        )
    }


    val evenements = buildList {
        appels.forEach { appel ->
            add(
                Evenement(
                    id = appel.id,
                    relatedObjectId = appel.id,
                    title = appel.subject,
                    description = appel.notes,
                    startDate = appel.dateTime,
                    endDate = appel.dateTime,
                    syncHash = randomSyncHash("appel")
                )
            )
        }

        entretiens.forEach { entretien ->
            add(
                Evenement(
                    id = entretien.id,
                    relatedObjectId = entretien.id,
                    title = "Entretien",
                    description = entretien.preInterviewNotes,
                    startDate = entretien.dateTime,
                    endDate = entretien.dateTime + (entretien.durationMinutes?.times(60)
                        ?.times(1000) ?: (30 * 60 * 1000)),
                    syncHash = randomSyncHash("entretien")
                )
            )
        }

        relances.forEach { relance ->
            add(
                Evenement(
                    id = relance.id,
                    relatedObjectId = relance.id,
                    title = "Relance",
                    description = relance.notes,
                    startDate = relance.date,
                    endDate = relance.date,
                    syncHash = randomSyncHash("relance")
                )
            )
        }

        candidatures.forEach { candidature ->
            add(
                Evenement(
                    id = candidature.id,
                    relatedObjectId = candidature.id,
                    title = candidature.title,
                    description = candidature.notes,
                    startDate = candidature.applicationDate,
                    endDate = candidature.applicationDate,
                    syncHash = randomSyncHash("candidature")
                )
            )
        }
    }

    fun removeCandidature(id: String) {
        candidatures.find { it.id == id }?.isArchived = true
    }

    fun deleteCandidature(id: String) {
        candidatures.removeAll { it.id == id }
    }

    fun removeEntreprise(id: String) {
        entreprises.find { it.id == id }?.isArchived = true
    }

    fun deleteEntreprise(id: String) {
        entreprises.removeAll { it.id == id }
    }

    fun removeContact(id: String) {
        contacts.find { it.id == id }?.isArchived = true
    }

    fun deleteContact(id: String) {
        contacts.removeAll { it.id == id }
    }

    fun removeRelance(id: String) {
        relances.find { it.id == id }?.isArchived = true
    }

    fun deleteRelance(id: String) {
        relances.removeAll { it.id == id }
    }

    fun removeEntretien(id: String) {
        entretiens.find { it.id == id }?.isArchived = true
    }

    fun deleteEntretien(id: String) {
        entretiens.removeAll { it.id == id }
    }

    fun removeAppel(id: String) {
        appels.find { it.id == id }?.isArchived = true
    }

    fun deleteAppel(id: String) {
        appels.removeAll { it.id == id }
    }
}