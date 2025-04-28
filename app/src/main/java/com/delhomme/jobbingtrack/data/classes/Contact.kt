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
    val syncHash: String
)
