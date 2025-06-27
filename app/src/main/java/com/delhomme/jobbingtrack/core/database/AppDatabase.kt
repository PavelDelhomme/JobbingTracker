package com.delhomme.jobbingtrack.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.utils.Converters
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.application.data.sources.local.ApplicationDao
import com.delhomme.jobbingtrack.features.application.data.sources.local.ReferenceDao
import com.delhomme.jobbingtrack.features.calendar.data.dao.EventTypeDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.calendar.data.dao.EventDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventTypeEntity
import com.delhomme.jobbingtrack.features.call.data.dao.CallDao
import com.delhomme.jobbingtrack.features.call.data.dao.CallTypeDao
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallTypeEntity
import com.delhomme.jobbingtrack.features.company.data.dao.CompanyDao
import com.delhomme.jobbingtrack.features.company.data.dao.CompanyTypeDao
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyTypeEntity
import com.delhomme.jobbingtrack.features.contact.data.dao.ContactDao
import com.delhomme.jobbingtrack.features.contact.data.dao.DepartmentTypeDao
import com.delhomme.jobbingtrack.features.contact.data.dao.PositionTypeDao
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.DepartmentTypeEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.PositionTypeEntity
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.CVDao
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.CollaboratorDao
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.EducationDao
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.ExperienceDao
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.LanguageDao
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.ProjectDao
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.SkillDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpPlatformDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewDao
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewStyleDao
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewTypeDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStatusEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.features.profil.data.dao.ProfilDao
import com.delhomme.jobbingtrack.features.profil.data.entities.ProfilEntity
import com.delhomme.jobbingtrack.features.user.data.dao.UserDao
import com.delhomme.jobbingtrack.features.user.data.entities.UserEntity


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
        FollowUpContactCrossRef::class,
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

    abstract fun referenceDao(): ReferenceDao

    abstract fun educationDao(): EducationDao
    abstract fun experienceDao(): ExperienceDao
    abstract fun collaboratorDao(): CollaboratorDao

    abstract fun cvDao(): CVDao
    abstract fun languageDao(): LanguageDao
    abstract fun skillDao(): SkillDao
    abstract fun projectDao(): ProjectDao

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

    abstract fun profileDao(): ProfilDao

    abstract fun callTypeDao(): CallTypeDao

    abstract fun companyTypeDao(): CompanyTypeDao

    abstract fun userDao(): UserDao
}