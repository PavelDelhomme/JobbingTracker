package com.delhomme.jobbingtrack.companies

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider

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