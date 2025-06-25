package com.delhomme.jobbingtrack.datas.mappers

import com.delhomme.jobbingtrack.events.Event
import com.delhomme.jobbingtrack.events.EventEntity


fun EventEntity.toDomain(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        startDate = startDate ?: 0,
        endDate = endDate ?: 0,
        type = type,
        userId = base.userId,
        syncHash = base.syncHash,
        isArchived = base.isArchived,
        isDeleted = base.isDeleted,
        createdAt = base.createdAt,
        updatedAt = base.updatedAt,
        deletedAt = base.deletedAt ?: 0,
        archivedAt = base.archivedAt ?: 0,
        relatedObjectId = relatedObjectId,
    )
}