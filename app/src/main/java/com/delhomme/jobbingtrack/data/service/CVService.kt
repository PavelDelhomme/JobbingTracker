package com.delhomme.jobbingtrack.data.service

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody

val filePart = MultipartBody.Part.createFormData(
    "file", file.name, file.asRequestBody("application/pdf".toMediaTypeOrNull())
)
api.uploadCV(filePart)
