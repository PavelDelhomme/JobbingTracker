package com.delhomme.jobbingtrack.data.classes.followups

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider


data class FollowUp(
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
    val date: Long,
    val contactId: String?,
    val applicationId: String,
    val companyId: String,
    val responseStatusId: String?,
    val typeId: String?,
    val notes: String?,
) : HasIdProvider

