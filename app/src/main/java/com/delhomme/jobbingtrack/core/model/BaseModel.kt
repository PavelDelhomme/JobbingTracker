package com.delhomme.jobbingtrack.core.model

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider

abstract class BaseModel(
    override val id: String,
    open val userId: String,
    open val syncHash: String,
    open var isArchived: Boolean = false,
    open var isDeleted: Boolean = false,
    open var createdAt: Long,
    open var updatedAt: Long,
    open var deletedAt: Long? = null,
    open var archivedAt: Long? = null
) : HasIdProvider
