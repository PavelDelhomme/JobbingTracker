package com.delhomme.jobbingtrack.core.utils

import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity


fun resolveCompanyId(
    existingCall: CallEntity? = null,
    existingFollowUp: FollowUpEntity? = null,
    existingInterview: InterviewEntity? = null,
    existingContact: ContactEntity? = null,
    applications: List<ApplicationEntity>,
    followUps: List<FollowUpEntity>,
    linkedApplicationId: String? = null,
    linkedFollowUpId: String? = null,
    fallbackCompanyId: String? = null
): String? {
    // 1. Priorité : appel ou entretien ou relance ou contact existant
    listOfNotNull(
        existingCall?.company_id,
        existingInterview?.companyId,
        existingFollowUp?.companyId,
        existingContact?.companyId
    ).firstOrNull()?.takeIf { it.isNotBlank() }?.let { return it }

    // 2. Via une candidature liée (manuelle ou déduite via appel / relance)
    val candidatureId = listOfNotNull(
        existingCall?.application_id,
        existingFollowUp?.applicationId,
        existingInterview?.applicationId,
        existingContact?.applicationIds?.firstOrNull { id ->
            applications.any { it.id == id }
        },
        linkedApplicationId,
        linkedFollowUpId?.let { id -> followUps.find { it.id == id }?.applicationId }
    ).firstOrNull()

    candidatureId?.let { id ->
        applications.find { it.id == id }?.companyId?.takeIf { it.isNotBlank() }?.let { return it }
    }

    // 3. Fallback direct
    return fallbackCompanyId?.takeIf { it.isNotBlank() }
}