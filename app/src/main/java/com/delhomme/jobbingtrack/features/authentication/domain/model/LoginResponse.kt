package com.delhomme.jobbingtrack.features.authentication.domain.model

import com.delhomme.jobbingtrack.features.user.domain.models.UserInfo


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String,
    val user: UserInfo
)
