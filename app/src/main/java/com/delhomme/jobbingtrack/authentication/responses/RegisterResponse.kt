package com.delhomme.jobbingtrack.authentication.responses



data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)
