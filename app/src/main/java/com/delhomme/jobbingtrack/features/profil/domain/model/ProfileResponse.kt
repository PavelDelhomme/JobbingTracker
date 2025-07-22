package com.delhomme.jobbingtrack.features.profil.domain.model

data class ProfileResponse(
    val id: String,
    val bio: String,
    val phone: String,
    val website: String,
    val linkedin_url: String,
    val github_url: String,
    val target_salary_min: Double?,
    val target_salary_max: Double?,
    val target_locations: List<String>,
    val remote_work_preference: String,
    val stats: ProfileStats
)

data class ProfileStats(
    val apps_last_7: Int,
    val calls_last_7: Int,
    val fu_last_7: Int,
    val itw_last_7: Int,
    val contacts_last_7: Int
)