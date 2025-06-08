package com.delhomme.jobbingtrack.commons

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delhomme.jobbingtrack.applications.dao.ApplicationDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.applications.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationEntity
import com.delhomme.jobbingtrack.commons.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.contacts.dao.ContactDao
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.events.entities.EventEntity
import com.delhomme.jobbingtrack.profiles.entities.ProfilEntity
import com.delhomme.jobbingtrack.users.entities.UserEntity


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