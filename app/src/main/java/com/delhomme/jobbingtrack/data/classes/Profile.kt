package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class Profile(
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
    val avatarUrl: String? = null,
    val catchPhrase: String? = null,
    val subject: String,
    val skills_ids: List<String>,
    val notes: String?,
    val languages_ids: List<String>,
    val experiences_ids: List<String>,
    val educations_ids: List<String>,
    val projects_ids: List<String>,
    val cvs_ids: List<String>,
) : HasIdProvider
