package com.delhomme.jobbingtrack.data.classes

data class Entretien(
    val id: String,
    val candidatureId: String,
    val companyId: String,
    val dateTime: Long,
    val durationMinutes: Int?,
    val location: String?,
    val contacts: List<String>,
    val style: EntretienStyle,
    val type: EntretienType,
    val preInterviewNotes: String?,
    val interviewNotes: String?,
    val postInterviewNotes: String?,
    val returnDate: Long?,
    val testsNeeded: Boolean,
    val testsDeadline: Long?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
)

enum class EntretienStyle {
    ON_SITE, REMOTE
}

enum class EntretienType {
    RH, TECHNICAL
}
