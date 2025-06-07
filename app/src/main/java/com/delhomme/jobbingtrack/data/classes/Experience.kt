package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider


data class Experience(
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
    val title: String,
    val company: String,
    val description: String,
    val startDate: String,
    val endDate: String?
) : HasIdProvider