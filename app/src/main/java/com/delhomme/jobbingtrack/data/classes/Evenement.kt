package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class Evenement(
    override val id: String,
    val userId: String,
    val relatedObjectId: String?,
    val title: String,
    val description: String?,
    val startDate: Long,
    val endDate: Long?,
    val syncHash: String,
    val type: String, // Type d'évènement
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasIdProvider
