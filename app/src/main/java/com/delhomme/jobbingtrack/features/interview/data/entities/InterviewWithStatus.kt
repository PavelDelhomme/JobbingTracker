package com.delhomme.jobbingtrack.features.interview.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class InterviewWithStatus(
    @Embedded val interview: InterviewEntity,
    @Relation(parentColumn = "statusId", entityColumn = "id")
    val status: InterviewStatusEntity
)