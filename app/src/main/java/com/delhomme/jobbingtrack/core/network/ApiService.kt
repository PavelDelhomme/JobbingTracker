package com.delhomme.jobbingtrack.core.network

import com.delhomme.jobbingtrack.core.network.tokens.RefreshTokenRequest
import com.delhomme.jobbingtrack.core.network.tokens.RefreshTokenResponse
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationStatusEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationTypeEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ContractTypeEntity
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginResponse
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterResponse
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallTypeEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventTypeEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyTypeEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.DepartmentTypeEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.PositionTypeEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CollaboratorEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.EducationEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.ExperienceEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.LanguageEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.ProjectEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.SkillEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStatusEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.features.profil.domain.model.ProfileResponse
import com.delhomme.jobbingtrack.features.profil.requests.ProfileUpdateRequest
import com.delhomme.jobbingtrack.features.user.domain.models.UserInfo
import com.delhomme.jobbingtrack.services.sync.SyncResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // =================================
    // AUTHENTIFICATION
    // =================================
    @POST("api/auth/login/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/register/")
    suspend fun register(@Body body: RegisterRequest): Response<RegisterResponse>

    @POST("api/auth/refresh/")
    fun refreshToken(@Body body: RefreshTokenRequest): Response<RefreshTokenResponse>

    // =================================
    // PROFIL UTILISATEUR
    // =================================
    @GET("api/auth/me/")
    suspend fun getCurrentUser(): Response<UserInfo>

    @GET("api/profiles/")
    suspend fun getMyProfile(): Response<ProfileResponse>

    @POST("api/profiles/")
    suspend fun updateProfile(@Body profile: ProfileUpdateRequest): Response<ProfileResponse>

    @PUT("api/profiles/{profileId}/")
    suspend fun updateProfileById(
        @Path("profileId") profileId: String,
        @Body profile: ProfileUpdateRequest
    ): Response<ProfileResponse>

    // =================================
    // SYNCHRONISATION
    // =================================
    @GET("api/sync/")
    suspend fun syncData(@Query("updated_after") timestamp: Long): Response<SyncResponse>

    @POST("api/client-sync/")
    suspend fun clientSync(@Body data: Map<String, List<Any>>): Response<SyncResponse>

    // =================================
    // APPLICATIONS / CANDIDATURES
    // =================================
    @GET("api/applications/")
    suspend fun getApplications(): Response<List<ApplicationEntity>>

    @GET("api/applications/{id}/")
    suspend fun getApplicationById(@Path("id") id: String): Response<ApplicationEntity>

    @POST("api/applications/")
    suspend fun createApplication(@Body application: ApplicationEntity): Response<ApplicationEntity>

    @PUT("api/applications/{id}/")
    suspend fun updateApplication(
        @Path("id") id: String,
        @Body application: ApplicationEntity
    ): Response<ApplicationEntity>

    @DELETE("api/applications/{id}/")
    suspend fun deleteApplication(@Path("id") id: String): Response<Unit>

    @GET("api/applications/statuses/")
    suspend fun getApplicationStatuses(): Response<List<ApplicationStatusEntity>>

    @GET("api/applications/types/")
    suspend fun getApplicationTypes(): Response<List<ApplicationTypeEntity>>

    @GET("api/applications/platforms/")
    suspend fun getApplicationPlatforms(): Response<List<ApplicationPlatformEntity>>

    @GET("api/applications/contract-types/")
    suspend fun getContractTypes(): Response<List<ContractTypeEntity>>

    // =================================
    // COMPANIES / ENTREPRISES
    // =================================
    @GET("api/companies/")
    suspend fun getCompanies(): Response<List<CompanyEntity>>

    @GET("api/companies/{id}/")
    suspend fun getCompanyById(@Path("id") id: String): Response<CompanyEntity>

    @POST("api/companies/")
    suspend fun createCompany(@Body company: CompanyEntity): Response<CompanyEntity>

    @PUT("api/companies/{id}/")
    suspend fun updateCompany(
        @Path("id") id: String,
        @Body company: CompanyEntity
    ): Response<CompanyEntity>

    @DELETE("api/companies/{id}/")
    suspend fun deleteCompany(@Path("id") id: String): Response<Unit>

    @GET("api/companies/types/")
    suspend fun getCompanyTypes(): Response<List<CompanyTypeEntity>>

    // =================================
    // CONTACTS
    // =================================
    @GET("api/contacts/")
    suspend fun getContacts(): Response<List<ContactEntity>>

    @GET("api/contacts/{id}/")
    suspend fun getContactById(@Path("id") id: String): Response<ContactEntity>

    @POST("api/contacts/")
    suspend fun createContact(@Body contact: ContactEntity): Response<ContactEntity>

    @PUT("api/contacts/{id}/")
    suspend fun updateContact(
        @Path("id") id: String,
        @Body contact: ContactEntity
    ): Response<ContactEntity>

    @DELETE("api/contacts/{id}/")
    suspend fun deleteContact(@Path("id") id: String): Response<Unit>

    @GET("api/contacts/departments/")
    suspend fun getDepartmentTypes(): Response<List<DepartmentTypeEntity>>

    @GET("api/contacts/positions/")
    suspend fun getPositionTypes(): Response<List<PositionTypeEntity>>

    // =================================
    // CALLS / APPELS
    // =================================
    @GET("api/calls/")
    suspend fun getCalls(): Response<List<CallEntity>>

    @GET("api/calls/{id}/")
    suspend fun getCallById(@Path("id") id: String): Response<CallEntity>

    @POST("api/calls/")
    suspend fun createCall(@Body call: CallEntity): Response<CallEntity>

    @PUT("api/calls/{id}/")
    suspend fun updateCall(@Path("id") id: String, @Body call: CallEntity): Response<CallEntity>

    @DELETE("api/calls/{id}/")
    suspend fun deleteCall(@Path("id") id: String): Response<Unit>

    @GET("api/calls/types/")
    suspend fun getCallTypes(): Response<List<CallTypeEntity>>

    // =================================
    // FOLLOWUPS / RELANCES
    // =================================
    @GET("api/followups/")
    suspend fun getFollowUps(): Response<List<FollowUpEntity>>

    @GET("api/followups/{id}/")
    suspend fun getFollowUpById(@Path("id") id: String): Response<FollowUpEntity>

    @POST("api/followups/")
    suspend fun createFollowUp(@Body followUp: FollowUpEntity): Response<FollowUpEntity>

    @PUT("api/followups/{id}/")
    suspend fun updateFollowUp(
        @Path("id") id: String,
        @Body followUp: FollowUpEntity
    ): Response<FollowUpEntity>

    @DELETE("api/followups/{id}/")
    suspend fun deleteFollowUp(@Path("id") id: String): Response<Unit>

    @GET("api/followups/types/")
    suspend fun getFollowUpTypes(): Response<List<FollowUpTypeEntity>>

    @GET("api/followups/statuses/")
    suspend fun getFollowUpStatuses(): Response<List<FollowUpStatusEntity>>

    @GET("api/followups/platforms/")
    suspend fun getFollowUpPlatforms(): Response<List<FollowUpPlateformEntity>>

    // =================================
    // INTERVIEWS / ENTRETIENS
    // =================================
    @GET("api/interviews/")
    suspend fun getInterviews(): Response<List<InterviewEntity>>

    @GET("api/interviews/{id}/")
    suspend fun getInterviewById(@Path("id") id: String): Response<InterviewEntity>

    @POST("api/interviews/")
    suspend fun createInterview(@Body interview: InterviewEntity): Response<InterviewEntity>

    @PUT("api/interviews/{id}/")
    suspend fun updateInterview(
        @Path("id") id: String,
        @Body interview: InterviewEntity
    ): Response<InterviewEntity>

    @DELETE("api/interviews/{id}/")
    suspend fun deleteInterview(@Path("id") id: String): Response<Unit>

    @GET("api/interviews/types/")
    suspend fun getInterviewTypes(): Response<List<InterviewTypeEntity>>

    @GET("api/interviews/statuses/")
    suspend fun getInterviewStatuses(): Response<List<InterviewStatusEntity>>

    @GET("api/interviews/styles/")
    suspend fun getInterviewStyles(): Response<List<InterviewStyleEntity>>

    // =================================
    // EVENTS / CALENDAR
    // =================================
    @GET("api/events/")
    suspend fun getEvents(): Response<List<EventEntity>>

    @GET("api/events/{id}/")
    suspend fun getEventById(@Path("id") id: String): Response<EventEntity>

    @POST("api/events/")
    suspend fun createEvent(@Body event: EventEntity): Response<EventEntity>

    @PUT("api/events/{id}/")
    suspend fun updateEvent(@Path("id") id: String, @Body event: EventEntity): Response<EventEntity>

    @DELETE("api/events/{id}/")
    suspend fun deleteEvent(@Path("id") id: String): Response<Unit>

    @GET("api/events/types/")
    suspend fun getEventTypes(): Response<List<EventTypeEntity>>

    // =================================
    // CVS
    // =================================
    @GET("api/cvs/")
    suspend fun getCVs(): Response<List<CVEntity>>

    @GET("api/cvs/{id}/")
    suspend fun getCVById(@Path("id") id: String): Response<CVEntity>

    @POST("api/cvs/")
    suspend fun createCV(@Body cv: CVEntity): Response<CVEntity>

    @PUT("api/cvs/{id}/")
    suspend fun updateCV(@Path("id") id: String, @Body cv: CVEntity): Response<CVEntity>

    @DELETE("api/cvs/{id}/")
    suspend fun deleteCV(@Path("id") id: String): Response<Unit>

    // CV - Skills
    @GET("api/skills/")
    suspend fun getSkills(): Response<List<SkillEntity>>

    @POST("api/skills/")
    suspend fun createSkill(@Body skill: SkillEntity): Response<SkillEntity>

    @PUT("api/skills/{id}/")
    suspend fun updateSkill(@Path("id") id: String, @Body skill: SkillEntity): Response<SkillEntity>

    @DELETE("api/skills/{id}/")
    suspend fun deleteSkill(@Path("id") id: String): Response<Unit>

    // CV - Education
    @GET("api/education/")
    suspend fun getEducation(): Response<List<EducationEntity>>

    @POST("api/education/")
    suspend fun createEducation(@Body education: EducationEntity): Response<EducationEntity>

    @PUT("api/education/{id}/")
    suspend fun updateEducation(@Path("id") id: String, @Body education: EducationEntity): Response<EducationEntity>

    @DELETE("api/education/{id}/")
    suspend fun deleteEducation(@Path("id") id: String): Response<Unit>

    // CV - Experience
    @GET("api/experiences/")
    suspend fun getExperiences(): Response<List<ExperienceEntity>>

    @POST("api/experiences/")
    suspend fun createExperience(@Body experience: ExperienceEntity): Response<ExperienceEntity>

    @PUT("api/experiences/{id}/")
    suspend fun updateExperience(@Path("id") id: String, @Body experience: ExperienceEntity): Response<ExperienceEntity>

    @DELETE("api/experiences/{id}/")
    suspend fun deleteExperience(@Path("id") id: String): Response<Unit>

    // CV - Projects
    @GET("api/projects/")
    suspend fun getProjects(): Response<List<ProjectEntity>>

    @POST("api/projects/")
    suspend fun createProject(@Body project: ProjectEntity): Response<ProjectEntity>

    @PUT("api/projects/{id}/")
    suspend fun updateProject(@Path("id") id: String, @Body project: ProjectEntity): Response<ProjectEntity>

    @DELETE("api/projects/{id}/")
    suspend fun deleteProject(@Path("id") id: String): Response<Unit>

    // CV - Languages
    @GET("api/languages/")
    suspend fun getLanguages(): Response<List<LanguageEntity>>

    @POST("api/languages/")
    suspend fun createLanguage(@Body language: LanguageEntity): Response<LanguageEntity>

    @PUT("api/languages/{id}/")
    suspend fun updateLanguage(@Path("id") id: String, @Body language: LanguageEntity): Response<LanguageEntity>

    @DELETE("api/languages/{id}/")
    suspend fun deleteLanguage(@Path("id") id: String): Response<Unit>

    // CV - Collaborators
    @GET("api/collaborators/")
    suspend fun getCollaborators(): Response<List<CollaboratorEntity>>

    @POST("api/collaborators/")
    suspend fun createCollaborator(@Body collaborator: CollaboratorEntity): Response<CollaboratorEntity>

    @PUT("api/collaborators/{id}/")
    suspend fun updateCollaborator(@Path("id") id: String, @Body collaborator: CollaboratorEntity): Response<CollaboratorEntity>

    @DELETE("api/collaborators/{id}/")
    suspend fun deleteCollaborator(@Path("id") id: String): Response<Unit>
}