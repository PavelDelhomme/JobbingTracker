package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider

data class Entretien(
    override val id: String,
    val userId: String,
    val candidatureId: String,
    val companyId: String,
    val dateTime: Long,
    val durationMinutes: Int?,
    val location: String?,
    val contacts: List<String>,
    val style: EntretienStyle?,
    val type: EntretienType?,
    val preInterviewNotes: String?,
    val interviewNotes: String?,
    val postInterviewNotes: String?,
    val returnDate: Long?,
    val testsNeeded: Boolean,
    val testsDeadline: Long?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasIdProvider

enum class EntretienStyle {
    ON_SITE, REMOTE
}

enum class EntretienType {
    RH, TECHNICAL
}
