package com.delhomme.jobbingtrack.datas.daos.companies

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.delhomme.jobbingtrack.commons.entities.CallContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyCallCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyInterviewCrossRef
import com.delhomme.jobbingtrack.companies.Company
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationEntity
import com.delhomme.jobbingtrack.datas.entities.calls.CallEntity

import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity
import kotlinx.coroutines.flow.Flow


data class CompanyWithContacts(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyContactCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class CompanyWithCalls(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyCallCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "callId"
        )
    )
    val calls: List<CallEntity>
)

data class CompanyWithInterviews(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyInterviewCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)


data class CompanyWithFollowUps(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyFollowUpCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "followUpId"
        )
    )
    val followUps: List<FollowUpEntity>
)

data class CompanyWithApplications(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyApplicationCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>
)

data class CompanyFull(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyContactCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CompanyApplicationCrossRef::class,
            parentColumn = "companyId",
            entityColumn = "applicationId"
        )
    )
    val applications: List<ApplicationEntity>,
    // Ajoute ici d'autres relations si besoin
)


@Dao
interface CompanyDao {
    @Query("SELECT * FROM companies WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY name
    """)
    fun getAllActiveForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY name
    """)
    fun getArchivedForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY name
    """)
    fun getDeletedForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CompanyEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entreprise: CompanyEntity)

    @Update
    suspend fun update(entreprise: CompanyEntity)

    @Query("UPDATE companies SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE companies SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE companies SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM companies WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM companies WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getCompanyWithContacts(id: String, userId: String): Flow<CompanyWithContacts?>

    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getCompanyWithApplications(id: String, userId: String): Flow<CompanyWithApplications?>


    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getCompanyWithCalls(id: String, userId: String): Flow<CompanyWithCalls?>

    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getCompanyWithInterviews(id: String, userId: String): Flow<CompanyWithInterviews?>
    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getCompanyWithFollowUps(id: String, userId: String): Flow<CompanyWithFollowUps?>


    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getCompanyFull(id: String, userId: String): Flow<CompanyFull?>

    @Transaction
    @Query("""
        SELECT * FROM companies 
        WHERE userId = :userId AND isDeleted = 0 AND isArchived = 0
        ORDER BY name
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<CompanyWithContacts>>

    // === GESTION DES CROSSREF ===
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompanyContactCrossRef(crossRef: CompanyContactCrossRef)

    @Query("DELETE FROM CompanyContactCrossRef WHERE companyId = :companyId")
    suspend fun clearContactsForCompany(companyId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompanyApplicationCrossRef(crossRef: CompanyApplicationCrossRef)

    @Query("DELETE FROM CompanyApplicationCrossRef WHERE companyId = :companyId")
    suspend fun clearApplicationsForCompany(companyId: String)
}
