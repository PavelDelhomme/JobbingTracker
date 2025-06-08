package com.delhomme.jobbingtrack.data.services.cv

import com.delhomme.jobbingtrack.data.classes.users.Profile
import retrofit2.http.GET


interface ProfileService {
    @GET("api/profiles/")
    suspend fun getMyProfile(): Profile
}