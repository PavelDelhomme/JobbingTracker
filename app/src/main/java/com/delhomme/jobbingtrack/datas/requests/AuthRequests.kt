package com.delhomme.jobbingtrack.datas.requests



data class LoginRequest(
    val email: String,
    val password: String
)



data class RegisterRequest(
    val email: String,
    val password: String
)
