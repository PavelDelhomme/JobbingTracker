package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class User(
    override val id: String,
    val email: String,
    val passwordHash: String,
    val token: String?,
    val syncHash: String,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasIdProvider
