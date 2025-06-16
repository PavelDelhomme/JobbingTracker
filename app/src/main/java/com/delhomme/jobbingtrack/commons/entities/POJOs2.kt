package com.delhomme.jobbingtrack.commons.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationStatusEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ContractTypeEntity
import com.delhomme.jobbingtrack.datas.entities.calls.CallEntity
import com.delhomme.jobbingtrack.datas.entities.calls.CallTypeEntity
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyTypeEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.DepartmentTypeEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.PositionTypeEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.CVEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.EducationEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ExperienceEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.LanguageEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ProjectEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.SkillEntity
import com.delhomme.jobbingtrack.datas.entities.events.EventEntity
import com.delhomme.jobbingtrack.datas.entities.events.EventTypeEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpStatusEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpTypeEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStatusEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStyleEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewTypeEntity


// ============= APPLICATION POJOs =============
data class ApplicationWithContacts(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class ApplicationWithCalls(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationCallCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)

data class ApplicationWithFollowUps(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationFollowUpCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>
)

data class ApplicationWithInterviews(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationInterviewCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

data class ApplicationWithCompany(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)

data class ApplicationWithPlatform(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "platform",
        entityColumn = "id"
    )
    val platformEntity: ApplicationPlatformEntity?
)

data class ApplicationWithStatus(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "applicationStatus",
        entityColumn = "id"
    )
    val statusEntity: ApplicationStatusEntity?
)

data class ApplicationWithType(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "applicationType",
        entityColumn = "id"
    )
    val typeEntity: ApplicationTypeEntity?
)

data class ApplicationWithContractType(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "contractType",
        entityColumn = "id"
    )
    val contractTypeEntity: ContractTypeEntity?
)

data class ApplicationFull(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationCallCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationFollowUpCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationInterviewCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

// ============= COMPANY POJOs =============
data class CompanyWithContacts(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyContactCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class CompanyWithApplications(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyApplicationCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>
)

data class CompanyWithFollowUps(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyFollowUpCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>
)

data class CompanyWithCalls(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyCallCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)

data class CompanyWithInterviews(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyInterviewCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

data class CompanyWithType(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "type",
        entityColumn = "id"
    )
    val typeEntity: CompanyTypeEntity?
)

data class CompanyFull(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyContactCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyApplicationCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyFollowUpCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyCallCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyInterviewCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

// ============= CONTACT POJOs =============
data class ContactWithCompany(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)

data class ContactWithApplications(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>
)

data class ContactWithCalls(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ContactCallCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)

data class ContactWithFollowUps(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ContactFollowUpCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>
)

data class ContactWithInterviews(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ContactInterviewCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

data class ContactWithDepartmentType(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "department",
        entityColumn = "id"
    )
    val departmentType: DepartmentTypeEntity?
)

data class ContactWithPositionType(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "position",
        entityColumn = "id"
    )
    val positionType: PositionTypeEntity?
)

data class ContactFull(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationContactCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ContactCallCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ContactFollowUpCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ContactInterviewCrossRef::class,
            parentColumn = "contactId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)

// ============= CALL POJOs =============
data class CallWithCompany(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)

data class CallWithContacts(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CallContactCrossRef::class,
            parentColumn = "callId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class CallWithApplication(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "id"
    )
    val application: ApplicationEntity?
)

data class CallWithFollowUp(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "followUpId",
        entityColumn = "id"
    )
    val followUp: FollowUpEntity?
)

data class CallWithType(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "subject", // ou un champ typeId si vous en avez un
        entityColumn = "id"
    )
    val type: CallTypeEntity?
)

data class CallFull(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CallContactCrossRef::class,
            parentColumn = "callId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "id"
    )
    val application: ApplicationEntity?,
    @Relation(
        parentColumn = "followUpId",
        entityColumn = "id"
    )
    val followUp: FollowUpEntity?
)

// ============= INTERVIEW POJOs =============
data class InterviewWithContacts(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            InterviewContactCrossRef::class,
            parentColumn = "interviewId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class InterviewWithCompany(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)

data class InterviewWithApplication(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "id"
    )
    val application: ApplicationEntity?
)

data class InterviewWithStyle(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "styleId",
        entityColumn = "id"
    )
    val style: InterviewStyleEntity?
)

data class InterviewWithType(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "id"
    )
    val type: InterviewTypeEntity?
)

data class InterviewWithStatus(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "statusId", // si vous ajoutez ce champ
        entityColumn = "id"
    )
    val status: InterviewStatusEntity?
)

data class InterviewFull(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            InterviewContactCrossRef::class,
            parentColumn = "interviewId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "id"
    )
    val application: ApplicationEntity?,
    @Relation(
        parentColumn = "styleId",
        entityColumn = "id"
    )
    val style: InterviewStyleEntity?,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "id"
    )
    val type: InterviewTypeEntity?
)

// ============= FOLLOWUP POJOs =============
data class FollowUpWithContacts(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpContactCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class FollowUpWithCalls(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpCallCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)

data class FollowUpWithCompany(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)

data class FollowUpWithApplication(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "id"
    )
    val application: ApplicationEntity?
)

data class FollowUpWithPlatform(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "platformId",
        entityColumn = "id"
    )
    val platform: FollowUpPlateformEntity?
)

data class FollowUpWithStatus(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "statusId",
        entityColumn = "id"
    )
    val status: FollowUpStatusEntity?
)

data class FollowUpWithType(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "id"
    )
    val type: FollowUpTypeEntity?
)

data class FollowUpFull(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpContactCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpCallCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "id"
    )
    val application: ApplicationEntity?,
    @Relation(
        parentColumn = "platformId",
        entityColumn = "id"
    )
    val platform: FollowUpPlateformEntity?,
    @Relation(
        parentColumn = "statusId",
        entityColumn = "id"
    )
    val status: FollowUpStatusEntity?,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "id"
    )
    val type: FollowUpTypeEntity?
)

// ============= CV POJOs =============
data class CVWithSkills(
    @Embedded val cv: CVEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVSkillCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "skillId"
        )
    )
    val skills: List<SkillEntity>
)

data class CVWithExperiences(
    @Embedded val cv: CVEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVExperienceCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "experienceId"
        )
    )
    val experiences: List<ExperienceEntity>
)

data class CVWithEducations(
    @Embedded val cv: CVEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVEducationCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "educationId"
        )
    )
    val educations: List<EducationEntity>
)

data class CVWithProjects(
    @Embedded val cv: CVEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVProjectCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "projectId"
        )
    )
    val projects: List<ProjectEntity>
)

data class CVWithLanguages(
    @Embedded val cv: CVEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVLanguageCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "languageId"
        )
    )
    val languages: List<LanguageEntity>
)

data class CVFull(
    @Embedded val cv: CVEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVSkillCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "skillId"
        )
    )
    val skills: List<SkillEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVExperienceCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "experienceId"
        )
    )
    val experiences: List<ExperienceEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVEducationCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "educationId"
        )
    )
    val educations: List<EducationEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVProjectCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "projectId"
        )
    )
    val projects: List<ProjectEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CVLanguageCrossRef::class,
            parentColumn = "cvId",
            entityColumn = "languageId"
        )
    )
    val languages: List<LanguageEntity>
)

// ============= EVENT POJOs =============
data class EventWithType(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "type",
        entityColumn = "id"
    )
    val eventType: EventTypeEntity?
)
