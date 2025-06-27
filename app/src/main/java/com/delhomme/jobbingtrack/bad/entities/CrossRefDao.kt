package com.delhomme.jobbingtrack.bad.entities

import androidx.room.Entity
import androidx.room.Index


@Entity(
    primaryKeys = ["companyId", "applicationId"],
    indices = [ Index("applicationId") ]
)
data class CompanyApplicationCrossRef(
    val companyId: String,
    val applicationId: String
)
// 4) Company <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["companyId", "contactId"],
    indices = [ Index("contactId") ]
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

@Entity(
    primaryKeys = ["companyId", "eventId"],
    indices = [Index("eventId")]
)
data class CompanyEventCrossRef(
    val companyId: String,
    val eventId: String
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
    primaryKeys = ["contactId", "callId"],
    indices = [Index("callId")]
)
data class ContactCallCrossRef(
    val contactId: String,
    val callId: String
)


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

@Entity(
    primaryKeys = ["contactId", "interviewId"],
    indices = [Index("interviewId")]
)
data class ContactInterviewCrossRef(
    val contactId: String,
    val interviewId: String
)

@Entity(
    primaryKeys = ["interviewId", "contactId"],
    indices = [Index("contactId")]
)
data class InterviewContactCrossRef(
    val interviewId: String,
    val contactId: String
)

@Entity(
    primaryKeys = ["callId", "contactId"],
    indices = [Index("contactId")]
)
data class CallContactCrossRef(
    val callId: String,
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

@Entity(
    primaryKeys = ["skillId", "cvId"],
    indices = [Index("cvId")]
)
data class CVSkillCrossRef(
    val skillId: String,
    val cvId: String
)

@Entity(
    primaryKeys = ["projectId", "cvId"],
    indices = [Index("cvId")]
)
data class CVProjectCrossRef(
    val projectId: String,
    val cvId: String
)

@Entity(
    primaryKeys = ["languageId", "cvId"],
    indices = [Index("cvId")]
)
data class CVLanguageCrossRef(
    val languageId: String,
    val cvId: String
)

@Entity(
    primaryKeys = ["experienceId", "cvId"],
    indices = [Index("cvId")]
)
data class CVExperienceCrossRef(
    val experienceId: String,
    val cvId: String
)


@Entity(
    primaryKeys = ["educationId", "cvId"],
    indices = [Index("cvId")]
)
data class CVEducationCrossRef(
    val educationId: String,
    val cvId: String
)