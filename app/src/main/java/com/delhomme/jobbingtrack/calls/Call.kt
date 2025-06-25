package com.delhomme.jobbingtrack.calls

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.companies.CompanyEntity


data class Call(
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
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val applicationId: String?,
    val followUpId: String?,
    val dateTime: Long,
    val notes: String?,
) : HasIdProvider



data class Call(
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
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val applicationId: String?,
    val followUpId: String?,
    val dateTime: Long,
    val notes: String?,
) : HasIdProvider
