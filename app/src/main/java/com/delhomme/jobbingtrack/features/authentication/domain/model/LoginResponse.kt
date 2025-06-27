package com.delhomme.jobbingtrack.features.authentication.domain.model


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
