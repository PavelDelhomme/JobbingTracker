package com.delhomme.jobbingtrack.data.classes


data class Entreprise(
    val id: String,
    val name: String,
    val type: String?,
    val phone: String?,
    val email: String?,
    val hrEmail: String?,
    val address: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
)