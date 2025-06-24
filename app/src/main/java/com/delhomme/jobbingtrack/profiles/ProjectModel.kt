package com.delhomme.jobbingtrack.profiles

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class Project(
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
    val companyId: String,
    val title: String,
    val description: String,
    val collaborators_ids: List<String> = emptyList(),
) : HasIdProvider