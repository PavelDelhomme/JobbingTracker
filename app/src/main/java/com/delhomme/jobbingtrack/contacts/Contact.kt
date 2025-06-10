package com.delhomme.jobbingtrack.contacts

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class Contact(
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
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val companyId: String,
    val notes: String,
    val applicationIds: List<String>? = null,
    val interviewIds: List<String>? = null,
    val followUpIds: List<String>? = null,
    val callIds: List<String>? = null,
) : HasIdProvider