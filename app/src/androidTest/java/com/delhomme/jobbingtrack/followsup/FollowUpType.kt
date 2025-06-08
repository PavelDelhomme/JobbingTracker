package com.delhomme.jobbingtrack.followsup

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider


data class FollowUpType(
    override val id: String,
    val userId: String,
    val label: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,
) : HasIdProvider
