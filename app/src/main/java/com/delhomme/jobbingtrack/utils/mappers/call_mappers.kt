package com.delhomme.jobbingtrack.utils.mappers

import com.delhomme.jobbingtrack.data.local.entities.CallWithCompanies
import com.delhomme.jobbingtrack.data.classes.Call

fun CallWithCompanies.toDomain(): Call {
    return Call(
        id = this.call.id,
        subject = this.call.subject,
        companyId = this.call.companyId,
        contactId = this.call.contactId,
        applicationId = this.call.applicationId,
        dateTime = this.call.dateTime,
        notes = this.call.notes,
        syncHash = this.call.syncHash,
        followUpId = this.call.followUpId,
        userId = this.call.userId,
        isArchived = this.call.isArchived,
        isDeleted = this.call.isDeleted,
        createdAt = this.call.createdAt,
        updatedAt = this.call.updatedAt,
        deletedAt = this.call.deletedAt!!,
        archivedAt = this.call.archivedAt!!
    )
}