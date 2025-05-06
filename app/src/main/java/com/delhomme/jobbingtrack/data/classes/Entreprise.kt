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


fun Entreprise.toFormMap(): Map<String, String> {
    return mapOf(
        "id" to (id ?: ""),
        "name" to (name ?: ""),
        "type" to (type ?: ""),
        "phone" to (phone ?: ""),
        "email" to (email ?: ""),
        "hrEmail" to (hrEmail ?: ""),
        "address" to (address ?: ""),
        "syncHash" to (syncHash ?: ""),
        "isArchived" to (isArchived.toString()),
        "isDeleted" to (isDeleted.toString()),
    )
}
