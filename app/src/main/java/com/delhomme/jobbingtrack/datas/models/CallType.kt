package com.delhomme.jobbingtrack.datas.models

import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider


data class CallType(
    override val id: String,
    val name: String
) : HasIdProvider