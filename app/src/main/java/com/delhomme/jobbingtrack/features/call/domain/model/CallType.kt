package com.delhomme.jobbingtrack.features.call.domain.model

import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider

data class CallType(
    override val id: String,
    val name: String
) : HasIdProvider