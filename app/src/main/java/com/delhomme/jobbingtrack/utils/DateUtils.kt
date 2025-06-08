package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.local.entities.call.CallEntity
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity
import com.delhomme.jobbingtrack.data.local.entities.event.EventEntity
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpEntity
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

fun Long.toFormattedDate(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(this))
}

fun Long.toFormattedDateTime(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(this))
}


fun parseDateToMillis(input: String?): Long {
    return try {
        LocalDate.parse(input?.trim())
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    } catch (e: Exception) {
        System.currentTimeMillis()
    }
}

fun ApplicationEntity.toInstant()   = Instant.ofEpochMilli(applicationDate)
fun FollowUpEntity.toInstant()       = Instant.ofEpochMilli(date)
fun CallEntity.toInstant()         = Instant.ofEpochMilli(dateTime)
fun InterviewEntity.toInstant()     = Instant.ofEpochMilli(dateTime)
fun EventEntity.toInstant()         = startDate?.let { Instant.ofEpochMilli(it) }
fun EventEntity.toEndDateInstant()  = endDate?.let { Instant.ofEpochMilli(it) }