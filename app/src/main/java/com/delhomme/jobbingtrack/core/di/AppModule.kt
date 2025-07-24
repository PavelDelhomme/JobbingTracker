package com.delhomme.jobbingtrack.core.di

import android.content.Context
import androidx.room.Room
import com.delhomme.jobbingtrack.core.database.AppDatabase
import com.delhomme.jobbingtrack.features.application.data.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.features.application.data.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.features.application.data.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.features.application.data.dao.ContractTypeDao
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationPlatformRepository
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.features.application.data.sources.local.ApplicationDao
import com.delhomme.jobbingtrack.features.application.data.sources.local.ReferenceDao
import com.delhomme.jobbingtrack.features.calendar.data.dao.EventDao
import com.delhomme.jobbingtrack.features.calendar.data.dao.EventTypeDao
import com.delhomme.jobbingtrack.features.call.data.dao.CallDao
import com.delhomme.jobbingtrack.features.call.data.dao.CallTypeDao
import com.delhomme.jobbingtrack.features.company.data.dao.CompanyDao
import com.delhomme.jobbingtrack.features.company.data.dao.CompanyTypeDao
import com.delhomme.jobbingtrack.features.contact.data.dao.ContactDao
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
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewDao
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewStyleDao
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewTypeDao
import com.delhomme.jobbingtrack.features.profil.data.dao.ProfilDao
import com.delhomme.jobbingtrack.features.user.data.dao.UserDao
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
    fun provideProfileDao(db: AppDatabase): ProfilDao = db.profileDao()
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()


    @Provides
    fun provideApplicationRepository(dao: ApplicationDao, ref: ReferenceDao): ApplicationRepository =
        ApplicationRepository(dao, ref)

    @Provides
    fun provideApplicationPlatformRepository(dao: ApplicationPlatformDao): ApplicationPlatformRepository =
        ApplicationPlatformRepository(dao)
}