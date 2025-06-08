package com.delhomme.jobbingtrack.cv

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider

data class CV(
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
    val file: String,
    val uploadedAt: Long,
) : HasIdProvider