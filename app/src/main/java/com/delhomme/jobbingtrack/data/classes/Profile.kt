package com.delhomme.jobbingtrack.data.classes

data class Profile(
    val id: String,
    val userId: String,
    val cvs: List<String>,
    val skills: List<String>,
    val experiences: List<String>,
    val projects: List<String>,
    val languages: List<Language>,
    val syncHash: String
)

data class Language(
    val name: String,
    val level: String,
    val certification: String?
)
