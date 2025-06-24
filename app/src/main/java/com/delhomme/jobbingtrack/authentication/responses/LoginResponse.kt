package com.delhomme.jobbingtrack.authentication.responses


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
