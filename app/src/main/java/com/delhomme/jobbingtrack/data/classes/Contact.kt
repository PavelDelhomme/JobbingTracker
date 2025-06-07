package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider


data class Contact(
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
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val companyId: String,
    val notes: String,
) : HasIdProvider

fun Contact.toFormMap(): Map<String, String> {
    return mapOf(
        "firstName" to (firstName ?: ""),
        "lastName" to (lastName ?: ""),
        "phone" to (phone ?: ""),
        "email" to (email ?: ""),
        "position" to (position ?: ""),
        "department" to (department ?: ""),
        "companyId" to (companyId ?: ""),
        "notes" to (notes ?: ""),
    )
}
