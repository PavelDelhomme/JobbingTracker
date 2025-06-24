package com.delhomme.jobbingtrack.authentication.requests



data class RegisterRequest(
    val email: String,
    val password: String
)
