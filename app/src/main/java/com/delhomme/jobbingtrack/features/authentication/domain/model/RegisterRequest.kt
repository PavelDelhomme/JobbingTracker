package com.delhomme.jobbingtrack.features.authentication.domain.model



data class RegisterRequest(
    val email: String,
    val password: String
)
