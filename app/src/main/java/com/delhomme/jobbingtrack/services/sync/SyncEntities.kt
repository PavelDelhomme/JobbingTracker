package com.delhomme.jobbingtrack.services.sync

data class SyncResponse(
    val updatedRecords: Map<String, List<Any>>,
    val deletedRecords: Map<String, List<String>>,
    val timestamp: Long
)