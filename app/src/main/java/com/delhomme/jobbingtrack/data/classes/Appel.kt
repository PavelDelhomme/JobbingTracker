package com.delhomme.jobbingtrack.data.classes


data class Appel(
    val id: String,
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val candidatureId: String?,
    val relanceId: String?,
    val dateTime: Long,
    val notes: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
)

fun Appel.toFormMap(): Map<String, String> {
    return mapOf(
        "dateTime" to (dateTime?.toString() ?: ""),
        "subject" to (subject ?: ""),
        "companyId" to (companyId ?: ""),
        "contactId" to (contactId ?: ""),
        "candidatureId" to (candidatureId ?: ""),
        "relanceId" to (relanceId ?: ""),
        "notes" to (notes ?: "")
    )
}
