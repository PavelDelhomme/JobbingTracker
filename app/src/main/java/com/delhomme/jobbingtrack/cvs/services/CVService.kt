package com.delhomme.jobbingtrack.cvs.services

import android.util.Log
import com.delhomme.jobbingtrack.api.ApiClient
import com.delhomme.jobbingtrack.api.CV
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File

class CVService {
    suspend fun uploadCV(file: File): CV? {
        return try {
            val filePart = MultipartBody.Part.createFormData(
                name = "file",
                filename = file.name,
                body = file.asRequestBody("application/pdf".toMediaTypeOrNull())
            )
            val response = ApiClient.api.uploadCV(filePart)
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