package com.delhomme.jobbingtrack.commons.entities

import androidx.room.Entity
import androidx.room.Index


// ============= APPLICATION RELATIONS =============
@Entity(
    primaryKeys = ["applicationId", "contactId"],
    indices = [Index("contactId")]
)
data class ApplicationContactCrossRef(
    val applicationId: String,
    val contactId: String
)

@Entity(
    primaryKeys = ["applicationId", "callId"],
    indices = [Index("callId")]
)
data class ApplicationCallCrossRef(
    val applicationId: String,
    val callId: String
)

@Entity(
    primaryKeys = ["applicationId", "followUpId"],
    indices = [Index("followUpId")]
)
data class ApplicationFollowUpCrossRef(
    val applicationId: String,
    val followUpId: String
)

@Entity(
    primaryKeys = ["applicationId", "interviewId"],
    indices = [Index("interviewId")]
)
data class ApplicationInterviewCrossRef(
    val applicationId: String,
    val interviewId: String
)

// ============= COMPANY RELATIONS =============
@Entity(
    primaryKeys = ["companyId", "applicationId"],
    indices = [Index("applicationId")]
)
data class CompanyApplicationCrossRef(
    val companyId: String,
    val applicationId: String
)

@Entity(
    primaryKeys = ["companyId", "contactId"],
    indices = [Index("contactId")]
)
data class CompanyContactCrossRef(
    val companyId: String,
    val contactId: String
)

@Entity(
    primaryKeys = ["companyId", "followUpId"],
    indices = [Index("followUpId")]
)
data class CompanyFollowUpCrossRef(
    val companyId: String,
    val followUpId: String
)

@Entity(
    primaryKeys = ["companyId", "callId"],
    indices = [Index("callId")]
)
data class CompanyCallCrossRef(
    val companyId: String,
    val callId: String
)

@Entity(
    primaryKeys = ["companyId", "interviewId"],
    indices = [Index("interviewId")]
)
data class CompanyInterviewCrossRef(
    val companyId: String,
    val interviewId: String
)

// ============= CONTACT RELATIONS =============
@Entity(
    primaryKeys = ["contactId", "callId"],
    indices = [Index("callId")]
)
data class ContactCallCrossRef(
    val contactId: String,
    val callId: String
)

@Entity(
    primaryKeys = ["contactId", "followUpId"],
    indices = [Index("followUpId")]
)
data class ContactFollowUpCrossRef(
    val contactId: String,
    val followUpId: String
)

@Entity(
    primaryKeys = ["contactId", "interviewId"],
    indices = [Index("interviewId")]
)
data class ContactInterviewCrossRef(
    val contactId: String,
    val interviewId: String
)

// ============= INTERVIEW RELATIONS =============
@Entity(
    primaryKeys = ["interviewId", "contactId"],
    indices = [Index("contactId")]
)
data class InterviewContactCrossRef(
    val interviewId: String,
    val contactId: String
)

// ============= CALL RELATIONS =============
@Entity(
    primaryKeys = ["callId", "contactId"],
    indices = [Index("contactId")]
)
data class CallContactCrossRef(
    val callId: String,
    val contactId: String
)

// ============= FOLLOWUP RELATIONS =============
@Entity(
    primaryKeys = ["followUpId", "contactId"],
    indices = [Index("contactId")]
)
data class FollowUpContactCrossRef(
    val followUpId: String,
    val contactId: String
)

@Entity(
    primaryKeys = ["followUpId", "callId"],
    indices = [Index("callId")]
)
data class FollowUpCallCrossRef(
    val followUpId: String,
    val callId: String
)

// ============= CV RELATIONS =============
@Entity(
    primaryKeys = ["cvId", "skillId"],
    indices = [Index("skillId")]
)
data class CVSkillCrossRef(
    val cvId: String,
    val skillId: String
)

@Entity(
    primaryKeys = ["cvId", "experienceId"],
    indices = [Index("experienceId")]
)
data class CVExperienceCrossRef(
    val cvId: String,
    val experienceId: String
)

@Entity(
    primaryKeys = ["cvId", "educationId"],
    indices = [Index("educationId")]
)
data class CVEducationCrossRef(
    val cvId: String,
    val educationId: String
)

@Entity(
    primaryKeys = ["cvId", "projectId"],
    indices = [Index("projectId")]
)
data class CVProjectCrossRef(
    val cvId: String,
    val projectId: String
)

@Entity(
    primaryKeys = ["cvId", "languageId"],
    indices = [Index("languageId")]
)
data class CVLanguageCrossRef(
    val cvId: String,
    val languageId: String
)
