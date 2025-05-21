package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.HasId


data class Candidature(
    override val id: String,
    val userId: String,
    var title: String,
    var companyName: String,
    var companyId: String,
    var applicationDate: Long, // timestamp
    var location: String?,
    var platform: String?,
    var contractType: String?,
    var notes: String?,
    var applicationType: ApplicationType,
    var applicationStatus: ApplicationStatus,
    var syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long,
    var archivedAt: Long
) : HasId

enum class ApplicationType {
    SPONTANEOUS, OFFER
}

enum class ApplicationStatus {
    WAITING, TO_BE_FOLLOWED_UP, INTERVIEW_PENDING, REJECTED_WITHOUT_INTERVIEW, REJECTED_AFTER_INTERVIEW
}
