package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class Evenement(
    override val id: String,
    val userId: String,
    val relatedObjectId: String?,
    val title: String,
    val description: String?,
    val startDate: Long?,
    val endDate: Long?,
    val syncHash: String, // Type d'évènement
    val type: String,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Any,
    var archivedAt: Any,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false
) : HasIdProvider
