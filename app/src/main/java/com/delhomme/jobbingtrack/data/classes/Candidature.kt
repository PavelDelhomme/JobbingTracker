package com.delhomme.jobbingtrack.data.classes


data class Candidature(
    val id: String,
    val title: String,
    val companyName: String,
    val companyId: String,
    val applicationDate: Long, // timestamp
    val location: String?,
    val platform: String?,
    val contractType: String?,
    val notes: String?,
    val applicationType: ApplicationType,
    val applicationStatus: ApplicationStatus,
    val syncHash: String
)

enum class ApplicationType {
    SPONTANEOUS, OFFER
}

enum class ApplicationStatus {
    WAITING, TO_BE_FOLLOWED_UP, INTERVIEW_PENDING, REJECTED_WITHOUT_INTERVIEW, REJECTED_AFTER_INTERVIEW
}
