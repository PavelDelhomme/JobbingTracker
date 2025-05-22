package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.classes.ApplicationType
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

fun Any?.toFieldMap(): Map<String, String> {
    return when (this) {
        is com.delhomme.jobbingtrack.data.classes.Candidature -> mapOf(
            "title" to this.title,
            "companyName" to this.companyName,
            "applicationDate" to this.applicationDate.toString(),
            "platform" to (this.platform ?: ""),
            "contractType" to (this.contractType ?: ""),
            "location" to (this.location ?: ""),
            "isSpontaneous" to (this.applicationType == ApplicationType.SPONTANEOUS).toString(),
            "notes" to (this.notes ?: "")
        )
        is com.delhomme.jobbingtrack.data.classes.Appel -> mapOf(
            "subject" to (this.subject ?: ""),
            "companyId" to (this.companyId ?: ""),
            "contactId" to (this.contactId ?: ""),
            "candidatureId" to (this.candidatureId ?: ""),
            "notes" to (this.notes ?: ""),
            "dateTime" to this.dateTime.toString()
        )
        is com.delhomme.jobbingtrack.data.classes.Contact -> mapOf(
            "firstName" to (this.firstName ?: ""),
            "lastName" to (this.lastName ?: ""),
            "phone" to (this.phone ?: ""),
            "email" to (this.email ?: ""),
            "position" to (this.position ?: ""),
            "department" to (this.department ?: ""),
            "companyName" to (this.entrepriseId ?: ""),
            "notes" to ""
        )
        is com.delhomme.jobbingtrack.data.classes.Entretien -> mapOf(
            "candidatureId" to this.candidatureId,
            "companyId" to this.companyId,
            "location" to (this.location ?: ""),
            "contacts" to this.contacts.joinToString(","),
            "type" to (this.type?.name ?: ""),
            "style" to (this.style?.name ?: ""),
            "preInterviewNotes" to (this.preInterviewNotes ?: ""),
            "interviewNotes" to (this.interviewNotes ?: ""),
            "postInterviewNotes" to (this.postInterviewNotes ?: ""),
            "dateTime" to this.dateTime.toString(),
            "returnDate" to (this.returnDate?.toString() ?: ""),
            "testsNeeded" to this.testsNeeded.toString(),
            "testsDeadline" to (this.testsDeadline?.toString() ?: "")
        )
        is com.delhomme.jobbingtrack.data.classes.Relance -> mapOf(
            "date" to this.date.toString(),
            "candidatureId" to this.candidatureId,
            "companyId" to this.companyId,
            "contactId" to (this.contactId ?: ""),
            "type" to (this.type?.name ?: ""),
            "responseStatus" to (this.responseStatus?.name ?: ""),
            "notes" to (this.notes ?: "")
        )
        else -> emptyMap()
    }
}

/**
 * Convertit toutes les valeurs de la Map en String si besoin.
 * Plus besoin de passer les clés à la main.
 */
fun Map<String, Any?>.toSafeFieldMap(): MutableMap<String, String> {
    val mutableMap = mutableMapOf<String, String>()
    for ((key, value) in this) {
        mutableMap[key] = when (value) {
            is Long, is Int, is Double, is Date -> value.toString()
            is Enum<*> -> value.name
            is String -> value
            null -> ""
            else -> value.toString()
        }
    }
    return mutableMap
}


/**
 * Agrégation d'une liste d'entités datés par jour.
 */
fun <T> List<T>.countByDay(
    dateSelector: (T) -> Instant,
    start: Instant,
    end: Instant
): List<Pair<LocalDate, Int>> {
    return this
        .asSequence()
        .filter { item ->
            val ts = dateSelector(item).toEpochMilli()
            ts in start.toEpochMilli()..end.toEpochMilli()
        }
        .groupBy { item ->
            dateSelector(item)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        }
        .map { (day, items) -> day to items.size }
        .sortedBy { it.first }
}

