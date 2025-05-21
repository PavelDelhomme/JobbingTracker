package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.HasId

data class Profile(
    override val id: String,
    val userId: String,
    val cvs: List<String>,
    val skills: List<String>,
    val experiences: List<String>,
    val projects: List<String>,
    val languages: List<Language>,
    val syncHash: String,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId

data class Language(
    val name: String,
    val level: String,
    val certification: String?
)
