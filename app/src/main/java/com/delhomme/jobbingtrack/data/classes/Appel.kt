package com.delhomme.jobbingtrack.data.classes


data class Appel(
    val id: String,
    val subject: String,
    val companyId: String,
    val contactId: String?,
    val candidatureId: String?,
    val dateTime: Long,
    val notes: String?,
    val syncHash: String,
)