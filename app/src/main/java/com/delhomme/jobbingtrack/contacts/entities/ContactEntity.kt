package com.delhomme.jobbingtrack.contacts.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import com.delhomme.jobbingtrack.data.local.entities.CommonEntityFields
import java.util.UUID

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val position: String?,
    val department: String?,
    val companyId: String,
    val applicationIds: List<String>?,
    val notes: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider