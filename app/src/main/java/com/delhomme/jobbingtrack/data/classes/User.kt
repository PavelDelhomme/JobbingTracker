package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.HasId

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
) : HasId
