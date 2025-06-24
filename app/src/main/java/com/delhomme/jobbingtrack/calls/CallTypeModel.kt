package com.delhomme.jobbingtrack.calls

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class CallType(
    override val id: String,
    val name: String
) : HasIdProvider