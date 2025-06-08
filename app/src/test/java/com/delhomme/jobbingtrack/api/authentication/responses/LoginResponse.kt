package com.delhomme.jobbingtrack.api.authentication.responses


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
