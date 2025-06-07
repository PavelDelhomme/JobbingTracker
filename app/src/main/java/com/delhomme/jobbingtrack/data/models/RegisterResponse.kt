package com.delhomme.jobbingtrack.data.models

data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)
