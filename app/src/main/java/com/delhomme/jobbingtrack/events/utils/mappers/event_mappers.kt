package com.delhomme.jobbingtrack.events.utils.mappers


import com.delhomme.jobbingtrack.data.classes.events.Event
import com.delhomme.jobbingtrack.data.local.entities.event.EventEntity

fun EventEntity.toDomain(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        startDate = startDate ?: 0,
        endDate = endDate ?: 0,
        type = type,
        userId = userId,
        syncHash = syncHash,
        isArchived = isArchived,
        isDeleted = isDeleted,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt ?: 0,
        archivedAt = archivedAt ?: 0,
        relatedObjectId = relatedObjectId,
    )
}