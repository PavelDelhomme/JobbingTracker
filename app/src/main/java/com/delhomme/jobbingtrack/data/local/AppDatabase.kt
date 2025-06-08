package com.delhomme.jobbingtrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delhomme.jobbingtrack.data.local.dao.calls.CallDao
import com.delhomme.jobbingtrack.data.local.dao.applications.ApplicationDao
import com.delhomme.jobbingtrack.data.local.dao.applications.ApplicationPlatformDao
import com.delhomme.jobbingtrack.data.local.dao.applications.ApplicationStatusDao
import com.delhomme.jobbingtrack.data.local.dao.applications.ApplicationTypeDao
import com.delhomme.jobbingtrack.data.local.dao.cv.CVDao
import com.delhomme.jobbingtrack.data.local.dao.calls.CallTypeDao
import com.delhomme.jobbingtrack.data.local.dao.cv.CollaboratorDao
import com.delhomme.jobbingtrack.data.local.dao.contact.ContactDao
import com.delhomme.jobbingtrack.data.local.dao.event.EventDao
import com.delhomme.jobbingtrack.data.local.dao.company.CompanyDao
import com.delhomme.jobbingtrack.data.local.dao.cv.EducationDao
import com.delhomme.jobbingtrack.data.local.dao.event.EventTypeDao
import com.delhomme.jobbingtrack.data.local.dao.cv.ExperienceDao
import com.delhomme.jobbingtrack.data.local.dao.interviews.InterviewDao
import com.delhomme.jobbingtrack.data.local.dao.cv.ProfileDao
import com.delhomme.jobbingtrack.data.local.dao.followups.FollowUpDao
import com.delhomme.jobbingtrack.data.local.dao.followups.FollowUpPlateformDao
import com.delhomme.jobbingtrack.data.local.dao.followups.FollowUpStatusDao
import com.delhomme.jobbingtrack.data.local.dao.followups.FollowUpTypeDao
import com.delhomme.jobbingtrack.data.local.dao.interviews.InterviewStatusDao
import com.delhomme.jobbingtrack.data.local.dao.interviews.InterviewStyleDao
import com.delhomme.jobbingtrack.data.local.dao.interviews.InterviewTypeDao
import com.delhomme.jobbingtrack.data.local.dao.cv.LanguageDao
import com.delhomme.jobbingtrack.data.local.dao.cv.ProjectDao
import com.delhomme.jobbingtrack.data.local.dao.cv.SkillDao
import com.delhomme.jobbingtrack.data.local.dao.user.UserDao
import com.delhomme.jobbingtrack.data.local.entities.call.CallEntity
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.company.CompanyEntity
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity
import com.delhomme.jobbingtrack.data.local.entities.cv.ProfilEntity
import com.delhomme.jobbingtrack.data.local.entities.user.UserEntity

import com.delhomme.jobbingtrack.data.local.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.data.local.entities.event.EventEntity
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpEntity


@Database(
    entities = [
        CallEntity::class,
        ApplicationEntity::class,
        ContactEntity::class,
        CompanyEntity::class,
        InterviewEntity::class,
        FollowUpEntity::class,
        ProfilEntity::class,
        UserEntity::class,
        ApplicationContactCrossRef::class,
        InterviewContactCrossRef::class,
        CompanyApplicationCrossRef::class,
        EventEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao
    abstract fun applicationStatusDao(): ApplicationStatusDao
    abstract fun applicationTypeDao(): ApplicationTypeDao
    abstract fun applicationPlateformDao(): ApplicationPlatformDao

    abstract fun callDao(): CallDao

    abstract fun collaboratorDao(): CollaboratorDao

    abstract fun companyDao(): CompanyDao

    abstract fun contactDao(): ContactDao

    abstract fun cvDao(): CVDao

    abstract fun educationDao(): EducationDao

    abstract fun eventTypeDao(): EventTypeDao
    abstract fun eventDao(): EventDao

    abstract fun experienceDao(): ExperienceDao

    abstract fun followUpPlateformDao(): FollowUpPlateformDao
    abstract fun followUpStatusdao(): FollowUpStatusDao
    abstract fun followUpTypeDao(): FollowUpTypeDao
    abstract fun followUpDao(): FollowUpDao

    abstract fun interviewStatusDao(): InterviewStatusDao
    abstract fun interviewStyleDao(): InterviewStyleDao
    abstract fun interviewTypeDao(): InterviewTypeDao
    abstract fun interviewDao(): InterviewDao

    abstract fun languageDao(): LanguageDao
    abstract fun profileDao(): ProfileDao
    abstract fun projectDao(): ProjectDao
    abstract fun skillDao(): SkillDao

    abstract fun callTypeDao(): CallTypeDao

    abstract fun userDao(): UserDao

}