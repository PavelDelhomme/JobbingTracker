package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.HasId


data class Contact(
    override val id: String,
    val userId: String,
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val entrepriseId: String,
    val syncHash: String,
    val notes: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId

fun Contact.toFormMap(): Map<String, String> {
    return mapOf(
        "firstName" to (firstName ?: ""),
        "lastName" to (lastName ?: ""),
        "phone" to (phone ?: ""),
        "email" to (email ?: ""),
        "position" to (position ?: ""),
        "department" to (department ?: ""),
        "entrepriseId" to (entrepriseId ?: ""),
        "notes" to (notes ?: ""),
    )
}
