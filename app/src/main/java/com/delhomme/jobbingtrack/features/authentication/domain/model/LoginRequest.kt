package com.delhomme.jobbingtrack.features.authentication.domain.model


data class LoginRequest(
    val email: String,
    val password: String
)

