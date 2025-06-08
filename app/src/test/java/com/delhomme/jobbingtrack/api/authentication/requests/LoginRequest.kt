package com.delhomme.jobbingtrack.api.authentication.requests


data class LoginRequest(
    val email: String,
    val password: String
)