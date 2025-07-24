package com.delhomme.jobbingtrack.features.call.domain.model

import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider


data class Call(
    // Data d'héritage de HasIdProvider
    override val id: String,
    val userId: String,
    val sync_hash: String,
    var is_archived: Boolean = false,
    var is_deleted: Boolean = false,
    var created_at: Long,
    var updated_at: Long,
    var deleted_at: Long? = null,
    var archived_at: Long? = null,

    // Data propre
    val subject: String,
    val company_id: String,
    val contact_id: String?,
    val application_id: String?,
    val follow_up_id: String?,
    val datetime: Long,
    val notes: String?,
) : HasIdProvider