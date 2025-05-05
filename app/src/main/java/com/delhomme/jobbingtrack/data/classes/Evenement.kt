package com.delhomme.jobbingtrack.data.classes

data class Evenement(
    val id: String,
    val relatedObjectId: String?,
    val title: String,
    val description: String?,
    val startDate: Long,
    val endDate: Long?,
    val syncHash: String,
    val type: String // Type d'évènement
)
