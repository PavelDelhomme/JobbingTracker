package com.delhomme.jobbingtrack.data.models

data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)

data class UserInfo(
    val id: String,
    val email: String
)
