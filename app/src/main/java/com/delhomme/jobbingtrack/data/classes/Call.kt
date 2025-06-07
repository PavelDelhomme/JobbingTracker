package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider


data class Call(
    // Data d'héritage de HasIdProvider
    override val id: String,
    val userId: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,

    // Data propre
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val applicationId: String?,
    val followUpId: String?,
    val dateTime: Long,
    val notes: String?,
) : HasIdProvider

fun Call.toFormMap(): Map<String, String> {
    return mapOf(
        "dateTime" to (dateTime?.toString() ?: ""),
        "subject" to (subject ?: ""),
        "companyId" to (companyId ?: ""),
        "contactId" to (contactId ?: ""),
        "applicationId" to (applicationId ?: ""),
        "followUpId" to (followUpId ?: ""),
        "notes" to (notes ?: "")
    )
}
