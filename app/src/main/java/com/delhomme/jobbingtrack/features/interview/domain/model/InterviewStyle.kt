package com.delhomme.jobbingtrack.features.interview.domain.model

import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider


data class InterviewStyle (
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