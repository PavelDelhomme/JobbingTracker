package com.delhomme.jobbingtrack.data.classes

data class User(
    val id: String,
    val email: String,
    val passwordHash: String,
    val token: String?,
    val syncHash: String
)
