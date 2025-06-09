package com.delhomme.jobbingtrack.interviews.enumes

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class InterviewType (
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

