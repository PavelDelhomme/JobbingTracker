package com.delhomme.jobbingtrack.data.classes


data class Contact(
    val id: String,
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
)

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
