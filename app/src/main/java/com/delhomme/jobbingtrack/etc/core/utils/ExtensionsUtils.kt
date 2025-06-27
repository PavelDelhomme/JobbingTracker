package com.delhomme.jobbingtrack.etc.core.utils

import com.delhomme.jobbingtrack.followsup.FollowUp
import com.delhomme.jobbingtrack.followsup.FollowUpStatus
import com.delhomme.jobbingtrack.followsup.FollowUpType
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date


fun Any?.toFieldMap(): Map<String, String> {
    return when (this) {
        is Application -> mapOf(
            "title" to this.title,
            "companyName" to this.companyName,
            "applicationDate" to this.applicationDate.toString(),
            "platform" to (this.platform ?: ""),
            "contractType" to (this.contractType ?: ""),
            "location" to (this.location ?: ""),
            "isSpontaneous" to (this.applicationType == "SPONTANEOUS").toString(),
            "notes" to (this.notes ?: "")
        )
        is Call -> mapOf(
            "subject" to (this.subject ?: ""),
            "companyId" to (this.companyId ?: ""),
            "contactId" to (this.contactId ?: ""),
            "candidatureId" to (this.applicationId ?: ""),
            "notes" to (this.notes ?: ""),
            "dateTime" to this.dateTime.toString()
        )
        is Contact -> mapOf(
            "firstName" to (this.firstName ?: ""),
            "lastName" to (this.lastName ?: ""),
            "phone" to (this.phone ?: ""),
            "email" to (this.email ?: ""),
            "position" to (this.position ?: ""),
            "department" to (this.department ?: ""),
            "companyName" to (this.companyId ?: ""),
            "notes" to ""
        )
        is Interview -> mapOf(
            "candidatureId" to this.applicationId,
            "companyId" to this.companyId,
            "location" to (this.location ?: ""),
            "contacts" to this.contacts_ids.joinToString(","),
            "type" to (this.type?.label ?: ""),
            "style" to (this.style?.label ?: ""),
            "preInterviewNotes" to (this.preInterviewNotes ?: ""),
            "interviewNotes" to (this.interviewNotes ?: ""),
            "postInterviewNotes" to (this.postInterviewNotes ?: ""),
            "dateTime" to this.dateTime.toString(),
            "returnDate" to (this.returnDate?.toString() ?: ""),
            "testsNeeded" to this.testsNeeded.toString(),
            "testsDeadline" to (this.testsDeadline?.toString() ?: "")
        )
        is FollowUp -> mapOf(
            "date" to this.date.toString(),
            "candidatureId" to this.applicationId,
            "companyId" to this.companyId,
            "contactId" to (this.contactsIds ?: ""),
            "type" to (this.typeId ?: ""),
            "responseStatus" to (this.responseStatusId ?: ""),
            "notes" to (this.notes ?: "")
        )
        is Map<*, *> -> this.entries.associate { (key, value) ->
            key.toString() to when (value) {
                is Long, is Int, is Double, is Date -> value.toString()
                is Enum<*> -> value.name
                is String -> value
                null -> ""
                else -> value.toString()
            }
        }
        is FollowUpStatus -> mapOf(
            "id" to this.id,
            "label" to this.label,
        )
        is FollowUpType -> mapOf(
            "id" to this.id,
            "label" to this.label,
        )
        is InterviewStatusEntity -> mapOf(
            "id" to this.id,
            "label" to this.label,
        )
        is InterviewTypeEntity -> mapOf(
            "id" to this.id,
            "label" to this.label,
        )
        is InterviewStyleEntity -> mapOf(
            "id" to this.id,
            "label" to this.label,
        )
        else -> emptyMap()
    } as Map<String, String>
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
