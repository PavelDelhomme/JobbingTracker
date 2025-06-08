package com.delhomme.jobbingtrack.api.authentication.requests


data class RegisterRequest(
    val email: String,
    val password: String
)
