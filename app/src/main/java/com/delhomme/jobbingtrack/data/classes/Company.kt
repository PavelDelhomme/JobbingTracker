package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider


data class Company(
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
    var name: String,
    var type: String?,
    var phone: String?,
    var email: String?,
    var hrEmail: String?,
    var address: String?,
    var notes: String?,
) : HasIdProvider


fun Company.toFormMap(): Map<String, String> {
    return mapOf(
        "id" to (id ?: ""),
        "name" to (name ?: ""),
        "type" to (type ?: ""),
        "phone" to (phone ?: ""),
        "email" to (email ?: ""),
        "hrEmail" to (hrEmail ?: ""),
        "address" to (address ?: ""),
        "syncHash" to (syncHash ?: ""),
        "notes" to (notes ?: ""),
        "isArchived" to (isArchived.toString()),
        "isDeleted" to (isDeleted.toString()),
    )
}
