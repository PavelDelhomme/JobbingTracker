package com.delhomme.jobbingtrack.utils

fun Any?.toFieldMap(): Map<String, String> {
    return when (this) {
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
            "style" to this.style.name,
            "type" to this.type.name,
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
            "type" to this.type.name,
            "responseStatus" to this.responseStatus.name,
            "notes" to (this.notes ?: "")
        )
        else -> emptyMap()
    }
}
