package com.delhomme.jobbingtrack.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


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