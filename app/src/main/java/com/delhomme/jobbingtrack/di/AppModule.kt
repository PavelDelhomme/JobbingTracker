package com.delhomme.jobbingtrack.di

import android.content.Context
import androidx.room.Room
import com.delhomme.jobbingtrack.commons.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Room Database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "jobbingtrack_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // DAOs
    @Provides
    fun provideApplicationDao(db: AppDatabase): ApplicationDao = db.applicationDao()

    @Provides
    fun provideCompanyDao(db: AppDatabase): CompanyDao = db.companyDao()


    @Provides
    fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()


    @Provides
    fun provideCallDao(db: AppDatabase): CallDao = db.callDao()

    @Provides
    fun provideEventDao(db: AppDatabase): EventDao = db.eventDao()
    @Provides
    fun provideEventTypeDao(db: AppDatabase): EventTypeDao = db.eventTypeDao()
    @Provides
    fun provideApplicationPlatformDao(db: AppDatabase): ApplicationPlatformDao = db.applicationPlateformDao()
    @Provides
    fun provideApplicationStatusDao(db: AppDatabase): ApplicationStatusDao = db.applicationStatusDao()
    @Provides
    fun provideApplicationTypeDao(db: AppDatabase): ApplicationTypeDao = db.applicationTypeDao()
    @Provides
    fun provideContractTypeDao(db: AppDatabase): ContractTypeDao = db.contractTypeDao()
    @Provides
    fun provideCallTypeDao(db: AppDatabase): CallTypeDao = db.callTypeDao()
    @Provides
    fun provideCompanyTypeDao(db: AppDatabase): CompanyTypeDao = db.companyTypeDao()
    @Provides
    fun provideCollaboratorDao(db: AppDatabase): CollaboratorDao = db.collaboratorDao()
    @Provides
    fun provideCVDao(db: AppDatabase): CVDao = db.cvDao()
    @Provides
    fun provideEducationDao(db: AppDatabase): EducationDao = db.educationDao()
    @Provides
    fun provideExperienceDao(db: AppDatabase): ExperienceDao = db.experienceDao()
    @Provides
    fun provideLanguageDao(db: AppDatabase): LanguageDao = db.languageDao()
    @Provides
    fun provideProjectDao(db: AppDatabase): ProjectDao = db.projectDao()
    @Provides
    fun provideSkillDao(db: AppDatabase): SkillDao = db.skillDao()
    @Provides
    fun provideFollowUpDao(db: AppDatabase): FollowUpDao = db.followUpDao()
    @Provides
    fun provideFollowUpPlatformDao(db: AppDatabase): FollowUpPlatformDao = db.followUpPlateformDao()
    @Provides
    fun provideFollowUpStatusDao(db: AppDatabase): FollowUpStatusDao = db.followUpStatusDao()
    @Provides
    fun provideFollowUpTypeDao(db: AppDatabase): FollowUpTypeDao = db.followUpTypeDao()
    @Provides
    fun provideInterviewDao(db: AppDatabase): InterviewDao = db.interviewDao()
    @Provides
    fun provideInterviewTypeDao(db: AppDatabase): InterviewTypeDao = db.interviewTypeDao()
    @Provides
    fun provideInterviewStatusDao(db: AppDatabase): InterviewStatusDao = db.interviewStatusDao()
    @Provides
    fun provideInterviewStyleDao(db: AppDatabase): InterviewStyleDao = db.interviewStyleDao()
    @Provides
    fun provideProfileDao(db: AppDatabase): ProfileDao = db.profileDao()
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()


    @Provides
    fun provideApplicationRepository(dao: ApplicationDao): ApplicationRepository =
        ApplicationRepository(dao)

    @Provides
    fun provideApplicationPlatformRepository(dao: ApplicationPlatformDao): ApplicationPlatformRepository =
        ApplicationPlatformRepository(dao)
    fun provideApplicationStatusRepository(dao: ApplicationStatusDao): ApplicationStatusRepository =
        ApplicationStatusRepository(dao)
    fun provideApplicationTypeRepository(dao: ApplicationTypeDao): ApplicationTypeRepository = ApplicationTypeRepository(dao)
    fun provideContractTypeRepository(dao: ContractTypeDao): ContractTypeRepository =
        ContractTypeRepository(dao)
    fun provideCallRepository(dao: CallDao): CallRepository = CallRepository(dao)
    fun provideCallTypeRepository(dao: CallTypeDao): CallTypeRepository = CallTypeRepository(dao)
    fun provideCompanyRepository(dao: CompanyDao): CompanyRepository = CompanyRepository(dao)
    fun provideCompanyTypeRepository(dao: CompanyTypeDao): CompanyTypeRepository = CompanyTypeRepository(dao)
    fun provideContactRepository(dao: ContactDao): ContactRepository = ContactRepository(dao)
    fun provideCollaboratorRepository(dao: CollaboratorDao): CollaboratorRepository =
        CollaboratorRepository(dao)
    fun provideCVRepository(dao: CVDao): CvRepository = CvRepository(dao)
    fun provideEducationRepository(dao: EducationDao): EducationRepository = EducationRepository(dao)
    fun provideExperienceRepository(dao: ExperienceDao): ExperienceRepository =
        ExperienceRepository(dao)
    fun provideLanguageRepository(dao: LanguageDao): LanguageRepository = LanguageRepository(dao)
    fun provideProjectRepository(dao: ProjectDao): ProjectRepository = ProjectRepository(dao)
    fun provideSkillRepository(dao: SkillDao): SkillRepository = SkillRepository(dao)
    fun provideEventRepository(dao: EventDao): EventRepository = EventRepository(dao)
    fun provideEventTypeRepository(dao: EventTypeDao): EventTypeRepository = EventTypeRepository(dao)
    fun provideFollowUpRepository(
        followUpDao: FollowUpDao,
        followUpTypeDao: FollowUpTypeDao,
        followUpStatusDao: FollowUpStatusDao
    ): FollowUpRepository = FollowUpRepository(
        followUpDao,
        followUpTypeDao,
        followUpStatusDao
    )
    fun provideFollowUpPlateformRepository(dao: FollowUpPlatformDao): FollowUpPlatformRepository = FollowUpPlatformRepository(dao)
    fun provideFollowUpStatusRepository(dao: FollowUpStatusDao): FollowUpStatusRepository = FollowUpStatusRepository(dao)
    fun provideFollowUpTypeRepository(dao: FollowUpTypeDao): FollowUpTypeRepository = FollowUpTypeRepository(dao)
    fun provideInterviewRepository(dao: InterviewDao): InterviewRepository = InterviewRepository(dao)
    fun provideInterviewStatusRepository(dao: InterviewStatusDao): InterviewStatusRepository = InterviewStatusRepository(dao)
    fun provideInterviewStyleRepository(dao: InterviewStyleDao): InterviewStyleRepository = InterviewStyleRepository(dao)
    fun provideInterviewTypeRepository(dao: InterviewTypeDao): InterviewTypeRepository = InterviewTypeRepository(dao)
    fun provideProfilRepository(dao: ProfileDao): ProfilRepository = ProfilRepository(dao)
    fun provideUserRepository(dao: UserDao): UserRepository = UserRepository(dao)
}