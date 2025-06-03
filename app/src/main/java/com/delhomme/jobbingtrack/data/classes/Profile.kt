package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class Profile(
    override val id: String,
    val userId: String,
    val subject: String,
    val skills: List<String>,
    val notes: String?,
    val languages: List<Language>,
    val experiences: List<Experience>,
    val educations: List<Education>,
    val projects: List<Project>,
    val cvs: List<CV>,
    val syncHash: String,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasIdProvider


data class CV(val id: String, val file: String, val uploadedAt: Long)

data class Language(
    val id: String,
    val profile: String,
    val name: String,
    val level: String,
    val certification: String?,
    val userId: String,
    val createdAt: Long,
    val updatedAt: Long
)

data class Experience(val title: String, val company: String, val description: String, val startDate: String, val endDate: String?)
data class Education(val diploma: String, val school: String, val startDate: String, val endDate: String?)
data class Project(val title: String, val description: String)