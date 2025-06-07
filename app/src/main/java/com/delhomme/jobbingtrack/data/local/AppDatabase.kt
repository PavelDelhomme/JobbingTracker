package com.delhomme.jobbingtrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delhomme.jobbingtrack.data.local.dao.CallDao
import com.delhomme.jobbingtrack.data.local.dao.ApplicationDao
import com.delhomme.jobbingtrack.data.local.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.data.local.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.data.local.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.data.local.dao.CVDao
import com.delhomme.jobbingtrack.data.local.dao.CallTypeDao
import com.delhomme.jobbingtrack.data.local.dao.CollaboratorDao
import com.delhomme.jobbingtrack.data.local.dao.ContactDao
import com.delhomme.jobbingtrack.data.local.dao.EventDao
import com.delhomme.jobbingtrack.data.local.dao.CompanyDao
import com.delhomme.jobbingtrack.data.local.dao.EducationDao
import com.delhomme.jobbingtrack.data.local.dao.EventTypeDao
import com.delhomme.jobbingtrack.data.local.dao.ExperienceDao
import com.delhomme.jobbingtrack.data.local.dao.InterviewDao
import com.delhomme.jobbingtrack.data.local.dao.ProfileDao
import com.delhomme.jobbingtrack.data.local.dao.FollowUpDao
import com.delhomme.jobbingtrack.data.local.dao.FollowUpPlateformDao
import com.delhomme.jobbingtrack.data.local.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.data.local.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.data.local.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.data.local.dao.InterviewStyleDao
import com.delhomme.jobbingtrack.data.local.dao.InterviewTypeDao
import com.delhomme.jobbingtrack.data.local.dao.LanguageDao
import com.delhomme.jobbingtrack.data.local.dao.ProjectDao
import com.delhomme.jobbingtrack.data.local.dao.SkillDao
import com.delhomme.jobbingtrack.data.local.dao.UserDao
import com.delhomme.jobbingtrack.data.local.entities.CallEntity
import com.delhomme.jobbingtrack.data.local.entities.ApplicationEntity
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.CompanyEntity
import com.delhomme.jobbingtrack.data.local.entities.InterviewEntity
import com.delhomme.jobbingtrack.data.local.entities.ProfilEntity
import com.delhomme.jobbingtrack.data.local.entities.UserEntity

import com.delhomme.jobbingtrack.data.local.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import com.delhomme.jobbingtrack.data.local.entities.FollowUpEntity


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