package com.delhomme.jobbingtrack.data.classes

import com.delhomme.jobbingtrack.data.HasId
import com.delhomme.jobbingtrack.utils.toFieldMap


data class Relance(
    override val id: String,
    val userId: String,
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

fun Relance.toSafeFieldMap() : Map<String, String> {
    val map = this.toFieldMap().toMutableMap()
    // Forcer la conversion propre de date en String
    map["date"] = this.date.toString()
    return map
}

enum class RelanceStatus {
    WAITING, POSITIVE_RESPONSE, NEGATIVE_RESPONSE, NO_RESPONSE
}

enum class RelanceType {
    CALL, EMAIL, PHYSICAL_VISIT
}