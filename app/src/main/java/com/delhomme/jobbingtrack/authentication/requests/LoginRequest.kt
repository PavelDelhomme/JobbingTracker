package com.delhomme.jobbingtrack.authentication.requests


data class LoginRequest(
    val email: String,
    val password: String
)

