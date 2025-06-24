package com.delhomme.jobbingtrack.applications

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class ApplicationType (
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
