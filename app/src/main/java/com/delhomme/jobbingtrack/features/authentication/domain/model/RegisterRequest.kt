package com.delhomme.jobbingtrack.features.authentication.domain.model



data class RegisterRequest(
    val email: String,
    val password: String,
    val password2: String,
    val first_name: String = "",
    val last_name: String = ""
)
