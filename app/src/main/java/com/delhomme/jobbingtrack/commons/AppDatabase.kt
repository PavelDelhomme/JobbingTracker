package com.delhomme.jobbingtrack.commons

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delhomme.jobbingtrack.applications.dao.ApplicationDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationEntity
import com.delhomme.jobbingtrack.calls.dao.CallDao
import com.delhomme.jobbingtrack.calls.dao.CallTypeDao
import com.delhomme.jobbingtrack.commons.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.contacts.dao.ContactDao
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.events.entities.EventEntity
import com.delhomme.jobbingtrack.profiles.entities.ProfilEntity
import com.delhomme.jobbingtrack.users.entities.UserEntity
import com.delhomme.jobbingtrack.calls.entities.CallEntity
import com.delhomme.jobbingtrack.companies.dao.CompanyDao
import com.delhomme.jobbingtrack.companies.entities.CompanyEntity
import com.delhomme.jobbingtrack.cvs.dao.CVDao
import com.delhomme.jobbingtrack.cvs.dao.CollaboratorDao
import com.delhomme.jobbingtrack.cvs.dao.EducationDao
import com.delhomme.jobbingtrack.cvs.dao.ExperienceDao
import com.delhomme.jobbingtrack.cvs.dao.LanguageDao
import com.delhomme.jobbingtrack.cvs.dao.ProjectDao
import com.delhomme.jobbingtrack.cvs.dao.SkillDao
import com.delhomme.jobbingtrack.events.dao.EventDao
import com.delhomme.jobbingtrack.events.dao.EventTypeDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpPlateformDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.followsup.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import com.delhomme.jobbingtrack.interviews.dao.InterviewDao
import com.delhomme.jobbingtrack.interviews.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.interviews.dao.InterviewStyleDao
import com.delhomme.jobbingtrack.interviews.dao.InterviewTypeDao
import com.delhomme.jobbingtrack.interviews.entities.InterviewEntity
import com.delhomme.jobbingtrack.profiles.dao.ProfileDao
import com.delhomme.jobbingtrack.users.dao.UserDao

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