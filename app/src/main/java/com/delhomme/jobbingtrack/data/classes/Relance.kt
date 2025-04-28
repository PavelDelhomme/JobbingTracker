package com.delhomme.jobbingtrack.data.classes



data class Relance(
    val id: String,
    val date: Long,
    val contactId: String?,
    val candidatureId: String,
    val companyId: String,
    val responseStatus: RelanceStatus,
    val type: RelanceType,
    val notes: String?,
    val syncHash: String
)

enum class RelanceStatus {
    WAITING, POSITIVE_RESPONSE, NEGATIVE_RESPONSE, NO_RESPONSE
}

enum class RelanceType {
    CALL, EMAIL, PHYSICAL_VISIT
}