package com.delhomme.jobbingtrack.commons

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delhomme.jobbingtrack.commons.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef

import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.applications.ApplicationEntity
import com.delhomme.jobbingtrack.applications.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.applications.ApplicationStatusEntity
import com.delhomme.jobbingtrack.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.applications.ContractTypeEntity
import com.delhomme.jobbingtrack.applications.dao.ApplicationDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.applications.dao.ContractTypeDao
import com.delhomme.jobbingtrack.calls.CallEntity
import com.delhomme.jobbingtrack.calls.CallTypeEntity
import com.delhomme.jobbingtrack.calls.dao.CallDao
import com.delhomme.jobbingtrack.calls.dao.CallTypeDao
import com.delhomme.jobbingtrack.commons.entities.ApplicationCallCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationInterviewCrossRef
import com.delhomme.jobbingtrack.commons.entities.CVEducationCrossRef
import com.delhomme.jobbingtrack.commons.entities.CVExperienceCrossRef
import com.delhomme.jobbingtrack.commons.entities.CVLanguageCrossRef
import com.delhomme.jobbingtrack.commons.entities.CVProjectCrossRef
import com.delhomme.jobbingtrack.commons.entities.CVSkillCrossRef
import com.delhomme.jobbingtrack.commons.entities.CallContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyCallCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyInterviewCrossRef
import com.delhomme.jobbingtrack.commons.entities.ContactCallCrossRef
import com.delhomme.jobbingtrack.commons.entities.ContactFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.ContactInterviewCrossRef
import com.delhomme.jobbingtrack.commons.entities.FollowUpCallCrossRef
import com.delhomme.jobbingtrack.companies.CompanyEntity
import com.delhomme.jobbingtrack.companies.CompanyTypeEntity
import com.delhomme.jobbingtrack.companies.dao.CompanyDao
import com.delhomme.jobbingtrack.companies.dao.CompanyTypeDao
import com.delhomme.jobbingtrack.contacts.ContactEntity
import com.delhomme.jobbingtrack.contacts.DepartmentTypeEntity
import com.delhomme.jobbingtrack.contacts.PositionTypeEntity
import com.delhomme.jobbingtrack.contacts.dao.ContactDao
import com.delhomme.jobbingtrack.contacts.dao.DepartmentTypeDao
import com.delhomme.jobbingtrack.contacts.dao.PositionTypeDao
import com.delhomme.jobbingtrack.events.EventEntity
import com.delhomme.jobbingtrack.events.EventTypeEntity
import com.delhomme.jobbingtrack.events.dao.EventDao
import com.delhomme.jobbingtrack.events.dao.EventTypeDao
import com.delhomme.jobbingtrack.followsup.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.followsup.FollowUpEntity
import com.delhomme.jobbingtrack.followsup.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.followsup.FollowUpStatusEntity
import com.delhomme.jobbingtrack.followsup.FollowUpTypeEntity
import com.delhomme.jobbingtrack.followsup.dao.FollowUpDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpPlatformDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.interviews.InterviewEntity
import com.delhomme.jobbingtrack.interviews.InterviewStatusEntity
import com.delhomme.jobbingtrack.interviews.InterviewStyleEntity
import com.delhomme.jobbingtrack.interviews.InterviewTypeEntity
import com.delhomme.jobbingtrack.interviews.dao.InterviewDao
import com.delhomme.jobbingtrack.interviews.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.interviews.dao.InterviewStyleDao
import com.delhomme.jobbingtrack.interviews.dao.InterviewTypeDao
import com.delhomme.jobbingtrack.profiles.ProfilEntity
import com.delhomme.jobbingtrack.profiles.dao.ProfileDao
import com.delhomme.jobbingtrack.users.UserEntity
import com.delhomme.jobbingtrack.users.dao.UserDao
import com.delhomme.jobbingtrack.utils.Converters

@Database(
    entities = [
        ApplicationEntity::class,
        CallEntity::class,
        ContactEntity::class,
        EventEntity::class,
        CompanyEntity::class,
        InterviewEntity::class,
        FollowUpEntity::class,
        ProfilEntity::class,
        UserEntity::class,
        ApplicationStatusEntity::class,
        ApplicationTypeEntity::class,
        ApplicationPlatformEntity::class,
        ContractTypeEntity::class,
        CallTypeEntity::class,
        DepartmentTypeEntity::class,
        PositionTypeEntity::class,
        EventTypeEntity::class,
        FollowUpPlateformEntity::class,
        FollowUpStatusEntity::class,
        FollowUpTypeEntity::class,
        InterviewStatusEntity::class,
        InterviewStyleEntity::class,
        InterviewTypeEntity::class,
        CompanyTypeEntity::class,

        // CrossRefs uniquement
        ApplicationContactCrossRef::class,
        ApplicationCallCrossRef::class,
        ApplicationFollowUpCrossRef::class,
        ApplicationInterviewCrossRef::class,
        CompanyContactCrossRef::class,
        CompanyApplicationCrossRef::class,
        CompanyCallCrossRef::class,
        CompanyFollowUpCrossRef::class,
        CompanyInterviewCrossRef::class,
        ContactCallCrossRef::class,
        ContactFollowUpCrossRef::class,
        ContactInterviewCrossRef::class,
        InterviewContactCrossRef::class,
        CallContactCrossRef::class,
        FollowUpContactCrossRef::class,
        FollowUpCallCrossRef::class,
        CVSkillCrossRef::class,
        CVProjectCrossRef::class,
        CVLanguageCrossRef::class,
        CVExperienceCrossRef::class,
        CVEducationCrossRef::class,
    ],

    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao
    abstract fun applicationStatusDao(): ApplicationStatusDao
    abstract fun applicationTypeDao(): ApplicationTypeDao
    abstract fun applicationPlateformDao(): ApplicationPlatformDao

    abstract fun contractTypeDao(): ContractTypeDao

    abstract fun callDao(): CallDao

    abstract fun companyDao(): CompanyDao
    abstract fun departmentTypeDao(): DepartmentTypeDao
    abstract fun positionTypeDao(): PositionTypeDao
    abstract fun contactDao(): ContactDao

    abstract fun eventTypeDao(): EventTypeDao
    abstract fun eventDao(): EventDao

    abstract fun followUpPlateformDao(): FollowUpPlatformDao
    abstract fun followUpStatusDao(): FollowUpStatusDao
    abstract fun followUpTypeDao(): FollowUpTypeDao
    abstract fun followUpDao(): FollowUpDao

    abstract fun interviewStatusDao(): InterviewStatusDao
    abstract fun interviewStyleDao(): InterviewStyleDao
    abstract fun interviewTypeDao(): InterviewTypeDao
    abstract fun interviewDao(): InterviewDao

    abstract fun profileDao(): ProfileDao

    abstract fun callTypeDao(): CallTypeDao

    abstract fun companyTypeDao(): CompanyTypeDao

    abstract fun userDao(): UserDao
}