package com.delhomme.jobbingtrack.api.authentication


data class LoginRequest(
    val email: String,
    val password: String
)



data class RegisterRequest(
    val email: String,
    val password: String
)
