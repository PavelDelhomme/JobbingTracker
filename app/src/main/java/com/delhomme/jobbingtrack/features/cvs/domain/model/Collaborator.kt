package com.delhomme.jobbingtrack.features.cvs.domain.model

import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider


data class Collaborator(
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
    val name: String,
    val role: String,
) : HasIdProvider