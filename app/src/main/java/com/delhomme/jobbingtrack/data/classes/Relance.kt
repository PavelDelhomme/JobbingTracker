package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.HasId


data class Relance(
    override val id: String,
    val date: Long,
    val contactId: String?,
    val candidatureId: String,
    val companyId: String,
    val responseStatus: RelanceStatus?,
    val type: RelanceType?,
    val notes: String?,
    val syncHash: String,
    var isArchived: Boolean = false,
    val isDeleted: Boolean = false,
) : HasId

enum class RelanceStatus {
    WAITING, POSITIVE_RESPONSE, NEGATIVE_RESPONSE, NO_RESPONSE
}

enum class RelanceType {
    CALL, EMAIL, PHYSICAL_VISIT
}