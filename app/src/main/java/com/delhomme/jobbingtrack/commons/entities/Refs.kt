package com.delhomme.jobbingtrack.commons.entities

import androidx.room.Entity
import androidx.room.Index


// 1) Application <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["applicationId", "contactId"],
    indices = [ Index("contactId") ]
)
data class ApplicationContactCrossRef(
    val applicationId: String,
    val contactId: String
)

// 2) Company <-> Application (n-à-n)
@Entity(
    primaryKeys = ["companyId", "applicationId"],
    indices = [ Index("applicationId") ]
)
data class CompanyApplicationCrossRef(
    val companyId: String,
    val applicationId: String
)

// 3) Interview <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["interviewId", "contactId"],
    indices = [ Index("contactId") ]
)
data class InterviewContactCrossRef(
    val interviewId: String,
    val contactId: String
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

// 5) Company <-> FollowUp (n-à-n)
@Entity(
    primaryKeys = ["companyId", "followUpId"],
    indices = [ Index("followUpId") ]
)
data class CompanyFollowUpCrossRef(
    val companyId: String,
    val followUpId: String
)

// 6) Company <-> Call (n-à-n)
@Entity(
    primaryKeys = ["companyId", "callId"],
    indices = [ Index("callId") ]
)
data class CompanyCallCrossRef(
    val companyId: String,
    val callId: String
)

// 7) Company <-> Interview (n-à-n)
@Entity(
    primaryKeys = ["companyId", "interviewId"],
    indices = [ Index("interviewId") ]
)
data class CompanyInterviewCrossRef(
    val companyId: String,
    val interviewId: String
)


// 9) Call <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["callId", "contactId"],
    indices = [ Index("contactId") ]
)
data class CallWithContactsCrossRef(
    val callId: String,
    val contactId: String
)