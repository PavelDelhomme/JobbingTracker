package com.delhomme.jobbingtrack.data.classes.applications

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class Application(
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

    // Data propres
    var title: String,
    var companyName: String,
    var companyId: String,
    var applicationDate: Long, // timestamp
    var location: String?,
    var platform: String?,
    var contractType: String?,
    var notes: String?,
    var applicationType: String,
    var applicationStatus: String,

) : HasIdProvider
