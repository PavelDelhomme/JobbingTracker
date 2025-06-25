package com.delhomme.jobbingtrack.authentication.responses

import com.delhomme.jobbingtrack.users.UserInfo


data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)
