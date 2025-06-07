package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.utils.toFieldMap


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

fun FollowUp.toSafeFieldMap() : Map<String, String> {
    val map = this.toFieldMap().toMutableMap()
    // Forcer la conversion propre de date en String
    map["date"] = this.date.toString()
    return map
}

data class FollowUpStatus(
    override val id: String,
    val userId: String,
    val label: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,
) : HasIdProvider

data class FollowUpType(
    override val id: String,
    val userId: String,
    val label: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,
) : HasIdProvider
