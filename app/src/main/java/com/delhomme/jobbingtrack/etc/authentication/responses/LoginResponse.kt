package com.delhomme.jobbingtrack.etc.authentication.responses


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
