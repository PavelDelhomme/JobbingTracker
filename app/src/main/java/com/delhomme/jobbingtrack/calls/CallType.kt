package com.delhomme.jobbingtrack.calls

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.companies.CompanyEntity


data class CallType(
    override val id: String,
    val name: String
) : HasIdProvider
