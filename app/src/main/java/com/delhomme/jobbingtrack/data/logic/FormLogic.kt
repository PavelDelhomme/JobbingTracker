package com.delhomme.jobbingtrack.data.logic


import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.local.entities.*
import com.delhomme.jobbingtrack.data.local.repository.*
import com.delhomme.jobbingtrack.utils.parseDateToMillis
import com.delhomme.jobbingtrack.utils.safeEnumValueOf
import kotlinx.coroutines.flow.first
import java.util.*

class FormLogic(
    private val candidatureRepo: CandidatureRepository,
    private val entrepriseRepo: EntrepriseRepository,
    private val contactRepo: ContactRepository,
    private val relanceRepo: RelanceRepository,
    private val appelRepo: AppelRepository,
    private val entretienRepo: EntretienRepository
) {

    suspend fun saveEntrepriseFromForm(data: Map<String, String>) {
        // 1) id existant ou nouveau
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val name = data["name"].orEmpty().trim()
        
        // 2) build entity
        val ent = data["userId"]?.let {
            EntrepriseEntity(
                id         = id,
                name       = name,
                type       = data["type"],
                phone      = data["phone"],
                email      = data["email"],
                hrEmail    = data["hrEmail"],
                address    = data["address"],
                notes      = data["notes"],
                isArchived = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
                isDeleted  = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
                userId     = it,
                syncHash   = "ent-${UUID.randomUUID()}"
            )
        }
        
        // 3) save
        if (ent != null) {
            entrepriseRepo.save(ent)
        };
    }
    
    suspend fun saveCandidatureFromForm(data: Map<String, String>) {
        val id = data['id']?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val title = data["title"].orEmpty().trim()
        // S'assurer que l'entrperise existe
        val companyName = data['companyName'].orEmpty().trim()
        var ent = entrepriseRepo.getAllForUser(data['userId']) // S'il faut filtrer par userId
            .first()
            .find { it.name.equals(companyName, true) }
        if (ent == null) {
            val newEnt = data["userId"]?.let {
                EntrepriseEntity(
                    id = UUID.randomUUID().toString(),
                    name = companyName,
                    userId = it,
                    type = null,
                    phone = null,
                    email = null,
                    hrEmail = null,
                    address = null,
                    notes = "",
                    isArchived = false,
                    isDeleted = false,
                    syncHash = "ent-${UUID.randomUUID()}"
                )
            }
            if (newEnt != null) {
                entrepriseRepo.save(newEnt)
                ent = newEnt
            }
        }
        
        val cand = data["userId"]?.let {
            CandidatureEntity(
                id = id,
                title = title,
                companyId = ent.id,
                applicationDate = parseDateToMillis(data["applicationDate"]),
                applicationStatus = (safeEnumValueOf<ApplicationStatus>(data["applicationStats"])
                    ?: ApplicationStatus.WAITING).toString(),
                applicationType = (safeEnumValueOf<ApplicationType>(data["applicationType"])
                    ?: ApplicationType.OFFER).toString(),
                location = data["location"],
                platform = data["platform"],
                contractType = data["contractType"],
                notes = data["notes"],
                isArchived = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
                isDeleted = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
                userId = it,
                syncHash = "cand-${UUID.randomUUID()}"
            )
        }
        if (cand != null) {
            candidatureRepo.save(cand)

        }
    }

    suspend fun saveContactFromForm(data: Map<String, String>) {
        val id    = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        // idem : créer ou retrouver l’entreprise
        val companyName = data["companyName"].orEmpty().trim()
        val ent = entrepriseRepo.getAllForUser("")
            .first()
            .find { e.name.equals(companyName, true) }
            ?: run {
                val e = EntrepriseEntity(
                    id         = UUID.randomUUID().toString(),
                    name       = companyName,
                    type       = null,
                    phone      = null,
                    email      = null,
                    hrEmail    = null,
                    address    = null,
                    notes      = "",
                    isArchived = false,
                    isDeleted  = false,
                    userId     = data["userId"].orEmpty(),
                    syncHash   = "ent-${UUID.randomUUID()}"
                )
                entrepriseRepo.save(e)
                e
            }

        val contact = ContactEntity(
            id           = id,
            firstName    = data["firstName"].orEmpty().trim(),
            lastName     = data["lastName"].orEmpty().trim(),
            phone        = data["phone"],
            email        = data["email"],
            position     = data["position"],
            department   = data["department"],
            entrepriseId = ent.id,
            notes        = data["notes"].orEmpty(),
            isArchived   = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted    = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            userId       = data["userId"].orEmpty(),
            syncHash     = "contact-${UUID.randomUUID()}",
            candidatureId = data["candidatureId"].orEmpty(),
        )
        contactRepo.save(contact)
    }


    suspend fun saveRelanceFromForm(data: Map<String, String>) {
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val rel = RelanceEntity(
            id             = id,
            date           = parseDateToMillis(data["date"]),
            candidatureId  = data["candidatureId"].orEmpty(),
            companyId      = data["companyId"].orEmpty(),
            contactId      = data["contactId"]?.takeIf(String::isNotBlank),
            type           = safeEnumValueOf<RelanceType>(data["type"]).toString(),
            responseStatus = safeEnumValueOf<RelanceStatus>(data["responseStatus"]).toString(),
            notes          = data["notes"],
            isArchived     = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted      = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            userId         = data["userId"].orEmpty(),
            syncHash       = "rel-${UUID.randomUUID()}"
        )
        relanceRepo.save(rel)
    }


    suspend fun saveAppelFromForm(data: Map<String, String>) {
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val appel = AppelEntity(
            id             = id,
            subject        = data["subject"].orEmpty(),
            companyId      = data["companyId"].orEmpty(),
            candidatureId  = data["candidatureId"]?.takeIf(String::isNotBlank),
            contactId      = data["contactId"]?.takeIf(String::isNotBlank),
            relanceId      = data["relanceId"]?.takeIf(String::isNotBlank),
            dateTime       = parseDateToMillis(data["dateTime"]),
            notes          = data["notes"],
            isArchived     = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted      = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            syncHash       = "appel-${UUID.randomUUID()}",
            userId         = data["userId"].orEmpty()
        )
        appelRepo.save(appel)
    }

    suspend fun saveEntretienFromForm(data: Map<String, String>) {
        val id = data["id"]?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
        val ent = EntretienEntity(
            id              = id,
            candidatureId   = data["candidatureId"].orEmpty(),
            companyId       = data["companyId"].orEmpty(),
            dateTime        = parseDateToMillis(data["dateTime"]),
            durationMinutes = data["durationMinutes"]?.toIntOrNull(),
            location        = data["location"],
            style           = safeEnumValueOf<EntretienStyle>(data["style"]).toString(),
            type            = safeEnumValueOf<EntretienType>(data["type"]).toString(),
            preInterviewNotes   = data["preInterviewNotes"],
            interviewNotes      = data["interviewNotes"],
            postInterviewNotes  = data["postInterviewNotes"],
            returnDate         = data["returnDate"]?.let { parseDateToMillis(it) },
            testsNeeded        = data["testsNeeded"]?.toBooleanStrictOrNull() ?: false,
            testsDeadline      = data["testsDeadline"]?.let { parseDateToMillis(it) },
            isArchived         = data["isArchived"]?.toBooleanStrictOrNull() ?: false,
            isDeleted          = data["isDeleted"]?.toBooleanStrictOrNull() ?: false,
            userId             = data["userId"].orEmpty(),
            syncHash           = "ent-${UUID.randomUUID()}",
        )
        // enlève d'abord les anciens contacts, puis recrée les crossrefs
        entretienRepo.save(ent, data["contacts"]?.split(",")?.map(String::trim).orEmpty())
    }
}