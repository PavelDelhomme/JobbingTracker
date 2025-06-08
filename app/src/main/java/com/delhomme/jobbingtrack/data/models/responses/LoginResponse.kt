package com.delhomme.jobbingtrack.data.models.responses

data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
