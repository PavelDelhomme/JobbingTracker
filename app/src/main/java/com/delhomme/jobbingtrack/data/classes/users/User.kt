package com.delhomme.jobbingtrack.data.classes.users

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class User(
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
    val email: String,
    val passwordHash: String,
    val token: String?,
) : HasIdProvider
