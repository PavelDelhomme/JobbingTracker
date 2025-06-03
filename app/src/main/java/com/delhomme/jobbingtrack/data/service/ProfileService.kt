package com.delhomme.jobbingtrack.data.service

import com.delhomme.jobbingtrack.data.classes.Profile
import retrofit2.http.GET


interface ProfileService {
    @GET("api/profiles/")
    suspend fun getMyProfile(): Profile
}