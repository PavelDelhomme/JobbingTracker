package com.delhomme.jobbingtrack.api.authentication

import com.delhomme.jobbingtrack.users.UserInfo


data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
