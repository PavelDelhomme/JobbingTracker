package com.delhomme.jobbingtrack.features.authentication.domain.model



data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)
