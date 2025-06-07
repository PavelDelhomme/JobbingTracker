package com.delhomme.jobbingtrack.data.logic


import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.local.entities.*
import com.delhomme.jobbingtrack.data.local.repository.*
import com.delhomme.jobbingtrack.utils.parseDateToMillis
import com.delhomme.jobbingtrack.utils.safeEnumValueOf
import kotlinx.coroutines.flow.first
import java.util.*

class FormLogic(
    private val applicationRepo: ApplicationRepository,
    private val companyRepo: CompanyRepository,
    private val contactRepo: ContactRepository,
    private val followUpRepo: FollowUpRepository,
    private val callRepo: CallRepository,
    private val interviewRepo: InterviewRepository,
    private val eventRepo: EventRepository,
) {

    suspend fun saveCompanyFromForm(data: Map<String, String>) {
        // 1) id existant ou nouveau
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val name = data["name"].orEmpty().trim()
        
        // 2) build entity
        val company = data["userId"]?.let {
            CompanyEntity(
                id         = id,
                name       = name,
                type       = data["type"],
                phone      = data["phone"],
                email      = data["email"],
                hrEmail    = data["hrEmail"],
                address    = data["address"],
                notes      = data["notes"],
                userId     = it,
                syncHash   = "company-${UUID.randomUUID()}"
            )
        }
        
        // 3) save
        if (company != null) {
            companyRepo.save(company)
        };
    }
    
    suspend fun saveApplicationFromForm(data: Map<String, String>) {
        val userId = data["userId"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        // 1) Cherche l'entreprise existante
        val companyName = data["companyName"].orEmpty().trim()
        var company = companyRepo
            .allForUser(userId)
            .first()
            .find { it.name.equals(companyName, true) }
            ?: CompanyEntity(
                id = UUID.randomUUID().toString(),
                name = companyName,
                userId = userId,
                syncHash = "company-${UUID.randomUUID()}"
            ).also { companyRepo.save(it) }
        // 2) Cherche la candidature existante
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val title = data["title"].orEmpty().trim()
        val application = data["userId"]?.let {
            ApplicationEntity(
                id = id,
                title = title,
                companyId = company.id,
                applicationDate = parseDateToMillis(data["applicationDate"]),
                applicationStatus = (safeEnumValueOf<ApplicationStatus>(data["applicationStatus"])
                    ?: ApplicationStatus.OPEN).toString(),
                applicationType = (safeEnumValueOf<ApplicationType>(data["applicationType"])
                    ?: ApplicationType.OFFER).toString(),
                location = data["location"],
                platform = data["platform"],
                contractType = data["contractType"],
                notes = data["notes"],
                userId = it,
                syncHash = "application-${UUID.randomUUID()}"
            )
        }
        if (application != null) {
            applicationRepo.save(application)
        }
    }

    suspend fun saveContactFromForm(data: Map<String, String>) {
        val id    = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val userId = data["userId"].orEmpty()
        // idem : créer ou retrouver l’entreprise
        val companyName = data["companyName"].orEmpty().trim()
        var cmp = companyRepo
            .allForUser(userId)
            .first()
            .find { it.name.equals(companyName, true) }
            ?: CompanyEntity(
                id = UUID.randomUUID().toString(),
                name = companyName,
                userId = userId,
                syncHash = "company-${UUID.randomUUID()}"
            ).also { companyRepo.save(it) }

        val contact = ContactEntity(
            id           = id,
            firstName    = data["firstName"].orEmpty().trim(),
            lastName     = data["lastName"].orEmpty().trim(),
            phone        = data["phone"],
            email        = data["email"],
            position     = data["position"],
            department   = data["department"],
            companyId    = cmp.id,
            notes        = data["notes"].orEmpty(),
            isArchived   = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted    = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            userId       = data["userId"].orEmpty(),
            syncHash     = "contact-${UUID.randomUUID()}",
            applicationIds = listOf(data["applicationIds"].orEmpty()),
        )
        contactRepo.save(contact)
    }


    suspend fun saveFollowUpFromForm(data: Map<String, String>) {
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val fol = FollowUpEntity(
            id             = id,
            date           = parseDateToMillis(data["date"]),
            applicationId  = data["applicationId"].orEmpty(),
            companyId      = data["companyId"].orEmpty(),
            contactId      = data["contactId"]?.takeIf(String::isNotBlank),
            type           = safeEnumValueOf<FollowUpType>(data["type"]).toString(),
            responseStatus = safeEnumValueOf<FollowUpStatus>(data["responseStatus"]).toString(),
            notes          = data["notes"],
            isArchived     = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted      = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            userId         = data["userId"].orEmpty(),
            syncHash       = "followup-${UUID.randomUUID()}"
        )
        followUpRepo.save(fol)
    }


    suspend fun saveCallFromForm(data: Map<String, String>) {
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val call = CallEntity(
            id             = id,
            subject        = data["subject"].orEmpty(),
            companyId      = data["companyId"].orEmpty(),
            applicationId  = data["applicationId"]?.takeIf(String::isNotBlank),
            contactId      = data["contactId"]?.takeIf(String::isNotBlank),
            followUpId      = data["followUpId"]?.takeIf(String::isNotBlank),
            dateTime       = parseDateToMillis(data["dateTime"]),
            notes          = data["notes"],
            isArchived     = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted      = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            syncHash       = "call-${UUID.randomUUID()}",
            userId         = data["userId"].orEmpty()
        )
        callRepo.save(call)
    }

    suspend fun saveInterviewFromForm(data: Map<String, String>) {
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val interview = InterviewEntity(
            id              = id,
            applicationId   = data["applicationId"].orEmpty(),
            companyId       = data["companyId"].orEmpty(),
            dateTime        = parseDateToMillis(data["dateTime"]),
            durationMinutes = data["durationMinutes"]?.toIntOrNull(),
            location        = data["location"],
            style           = safeEnumValueOf<InterviewStyle>(data["style"]).toString(),
            type            = safeEnumValueOf<InterviewType>(data["type"]).toString(),
            preInterviewNotes   = data["preInterviewNotes"],
            interviewNotes      = data["interviewNotes"],
            postInterviewNotes  = data["postInterviewNotes"],
            returnDate         = data["returnDate"]?.let { parseDateToMillis(it) },
            testsNeeded        = data["testsNeeded"]?.toBooleanStrictOrNull() ?: false,
            testsDeadline      = data["testsDeadline"]?.let { parseDateToMillis(it) },
            isArchived         = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted          = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            userId             = data["userId"].orEmpty(),
            syncHash           = "interview-${UUID.randomUUID()}",
        )
        // enlève d'abord les anciens contacts, puis recrée les crossrefs
        interviewRepo.save(interview, data["contacts"]?.split(",")?.map(String::trim).orEmpty())
    }
}