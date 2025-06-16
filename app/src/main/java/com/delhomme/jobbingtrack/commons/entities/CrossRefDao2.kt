package com.delhomme.jobbingtrack.commons.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface CrossRefDao {
    // Application CrossRefs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplicationContactCrossRef(crossRef: ApplicationContactCrossRef)

    @Delete
    suspend fun deleteApplicationContactCrossRef(crossRef: ApplicationContactCrossRef)

    // Company CrossRefs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompanyContactCrossRef(crossRef: CompanyContactCrossRef)

    @Delete
    suspend fun deleteCompanyContactCrossRef(crossRef: CompanyContactCrossRef)

    // Follow-up CrossRefs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpContactCrossRef(crossRef: FollowUpContactCrossRef)

    @Delete
    suspend fun deleteFollowUpContactCrossRef(crossRef: FollowUpContactCrossRef)

    // Interview CrossRefs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInterviewContactCrossRef(crossRef: InterviewContactCrossRef)

    @Delete
    suspend fun deleteInterviewContactCrossRef(crossRef: InterviewContactCrossRef)

    // Call CrossRefs
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCallContactCrossRef(crossRef: CallContactCrossRef)

    @Delete
    suspend fun deleteCallContactCrossRef(crossRef: CallContactCrossRef)
}
