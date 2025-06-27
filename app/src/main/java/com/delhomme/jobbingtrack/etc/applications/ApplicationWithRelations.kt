package com.delhomme.jobbingtrack.etc.applications

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.calls.CallEntity
import com.delhomme.jobbingtrack.contacts.ContactEntity
import com.delhomme.jobbingtrack.followsup.FollowUpEntity
import com.delhomme.jobbingtrack.interviews.InterviewEntity


data class ApplicationWithRelations(
    @Embedded val application: ApplicationEntity,
    @Relation(parentColumn = "contactIds", entityColumn = "id")
    val contacts: List<ContactEntity>,
    @Relation(parentColumn = "callIds", entityColumn = "id")
    val calls: List<CallEntity>,
    @Relation(parentColumn = "interviewIds", entityColumn = "id")
    val interviews: List<InterviewEntity>,
    @Relation(parentColumn = "followUpIds", entityColumn = "id")
    val followUps: List<FollowUpEntity>
)