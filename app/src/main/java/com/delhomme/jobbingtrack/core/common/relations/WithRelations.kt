package com.delhomme.jobbingtrack.core.common.relations

import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity

interface WithRelations<T> {
    val mainEntity: T
    val contacts: List<ContactEntity>
    val applications: List<ApplicationEntity>
    val companies: List<CompanyEntity>
    val calls: List<CallEntity>
    val followUps: List<FollowUpEntity>
    val interviews: List<InterviewEntity>
    val events: List<EventEntity>
    val cvs: List<CVEntity>
}