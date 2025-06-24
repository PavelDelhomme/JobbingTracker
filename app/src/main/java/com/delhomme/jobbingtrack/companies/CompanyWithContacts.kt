package com.delhomme.jobbingtrack.companies

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.commons.entities.CompanyApplicationCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.CompanyInterviewCrossRef
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity


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
