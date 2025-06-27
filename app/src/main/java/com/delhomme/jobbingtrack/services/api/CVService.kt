package com.delhomme.jobbingtrack.services.api

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject


class CVService @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun uploadCV(file: File): CV? {
        return try {
            val filePart = MultipartBody.Part.createFormData(
                name = "file",
                filename = file.name,
                body = file.asRequestBody("application/pdf".toMediaTypeOrNull())
            )
            val response = apiService.uploadCV(filePart)
            if (response != null) {
                Log.d("CVService", "CV uploaded: ${response.id}")
            }
            response
        } catch (e: Exception) {
            Log.e("CVService", "Erreur uploadCV", e)
            null
        }
    }
}
