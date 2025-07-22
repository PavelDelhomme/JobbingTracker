package com.delhomme.jobbingtrack.features.user.domain.models


data class UserInfo(
    val id: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val is_active: Boolean,
    val date_joined: String
)

