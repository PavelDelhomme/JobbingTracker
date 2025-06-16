package com.delhomme.jobbingtrack.datas.models

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class Event(
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
    val relatedObjectId: String?,
    val title: String,
    val description: String?,
    val startDate: Long?,
    val endDate: Long?,
    val type: String,
) : HasIdProvider