package com.delhomme.jobbingtrack.data.models.responses

import com.delhomme.jobbingtrack.data.models.user.UserInfo

data class RegisterResponse(
    val access: String,
    val refresh: String,
    val user: UserInfo
)
