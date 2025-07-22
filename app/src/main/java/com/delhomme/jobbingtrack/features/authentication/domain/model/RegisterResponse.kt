package com.delhomme.jobbingtrack.features.authentication.domain.model

import com.delhomme.jobbingtrack.features.user.domain.models.UserInfo


data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)
