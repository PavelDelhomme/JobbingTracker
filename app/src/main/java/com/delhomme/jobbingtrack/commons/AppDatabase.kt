package com.delhomme.jobbingtrack.commons

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.delhomme.jobbingtrack.commons.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef

import androidx.room.TypeConverters
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
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationDao
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationPlatformDao
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationStatusDao
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationTypeDao
import com.delhomme.jobbingtrack.datas.daos.applications.ContractTypeDao
import com.delhomme.jobbingtrack.datas.daos.calls.CallDao
import com.delhomme.jobbingtrack.datas.daos.calls.CallTypeDao
import com.delhomme.jobbingtrack.datas.daos.companies.CompanyDao
import com.delhomme.jobbingtrack.datas.daos.companies.CompanyTypeDao
import com.delhomme.jobbingtrack.datas.daos.contacts.ContactDao
import com.delhomme.jobbingtrack.datas.daos.contacts.DepartmentTypeDao
import com.delhomme.jobbingtrack.datas.daos.contacts.PositionTypeDao
import com.delhomme.jobbingtrack.datas.daos.cvs.CVDao
import com.delhomme.jobbingtrack.datas.daos.cvs.CollaboratorDao
import com.delhomme.jobbingtrack.datas.daos.cvs.EducationDao
import com.delhomme.jobbingtrack.datas.daos.cvs.ExperienceDao
import com.delhomme.jobbingtrack.datas.daos.cvs.LanguageDao
import com.delhomme.jobbingtrack.datas.daos.cvs.ProjectDao
import com.delhomme.jobbingtrack.datas.daos.cvs.SkillDao
import com.delhomme.jobbingtrack.datas.daos.events.EventDao
import com.delhomme.jobbingtrack.datas.daos.events.EventTypeDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpPlatformDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpStatusDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpTypeDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewStatusDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewStyleDao
import com.delhomme.jobbingtrack.datas.daos.interviews.InterviewTypeDao
import com.delhomme.jobbingtrack.datas.daos.profiles.ProfileDao
import com.delhomme.jobbingtrack.datas.daos.users.UserDao
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
import com.delhomme.jobbingtrack.datas.entities.cvs.CollaboratorEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.EducationEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ExperienceEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.LanguageEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ProjectEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.SkillEntity
import com.delhomme.jobbingtrack.datas.entities.events.EventEntity
import com.delhomme.jobbingtrack.datas.entities.events.EventTypeEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpStatusEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpTypeEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStatusEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStyleEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewTypeEntity
import com.delhomme.jobbingtrack.datas.entities.profiles.ProfilEntity
import com.delhomme.jobbingtrack.datas.entities.users.UserEntity
import com.delhomme.jobbingtrack.utils.Converters

@Database(
    entities = [
        ApplicationEntity::class,
        CallEntity::class,
        ContactEntity::class,
        EventEntity::class,
        CVEntity::class,
        EducationEntity::class,
        CompanyEntity::class,
        InterviewEntity::class,
        FollowUpEntity::class,
        ProfilEntity::class,
        CollaboratorEntity::class,
        UserEntity::class,
        ExperienceEntity::class,
        LanguageEntity::class,
        ProjectEntity::class,
        SkillEntity::class,
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

    abstract fun collaboratorDao(): CollaboratorDao

    abstract fun companyDao(): CompanyDao
    abstract fun departmentTypeDao(): DepartmentTypeDao
    abstract fun positionTypeDao(): PositionTypeDao
    abstract fun contactDao(): ContactDao

    abstract fun cvDao(): CVDao

    abstract fun educationDao(): EducationDao

    abstract fun eventTypeDao(): EventTypeDao
    abstract fun eventDao(): EventDao

    abstract fun experienceDao(): ExperienceDao

    abstract fun followUpPlateformDao(): FollowUpPlatformDao
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
/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * @return a Dao for [SkillEntity]
     */
/* <<<<<<<<<<  07b67ddf-6fea-4051-8616-38efb9462649  >>>>>>>>>>> */
    abstract fun skillDao(): SkillDao

    abstract fun callTypeDao(): CallTypeDao

    abstract fun companyTypeDao(): CompanyTypeDao

    abstract fun userDao(): UserDao
}