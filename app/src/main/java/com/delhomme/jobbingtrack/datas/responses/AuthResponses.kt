package com.delhomme.jobbingtrack.datas.responses

import com.delhomme.jobbingtrack.datas.models.UserInfo


data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)


data class LoginResponse(
    val access: String, // Token JWT reçu
    val refresh: String
)
