package com.delhomme.jobbingtrack.calls.mappers
import com.delhomme.jobbingtrack.calls.CallWithCompany
import com.delhomme.jobbingtrack.datas.models.Call

fun CallWithCompany.toDomain(): Call {
    return Call(
        id = this.call.id,
        subject = this.call.subject,
        companyId = this.call.companyId,
        contactId = this.call.contactId,
        applicationId = this.call.applicationId,
        dateTime = this.call.dateTime,
        notes = this.call.notes,
        syncHash = this.call.base.syncHash,
        followUpId = this.call.followUpId,
        userId = this.call.base.userId,
        isArchived = this.call.base.isArchived,
        isDeleted = this.call.base.isDeleted,
        createdAt = this.call.base.createdAt,
        updatedAt = this.call.base.updatedAt,
        deletedAt = this.call.base.deletedAt,
        archivedAt = this.call.base.archivedAt
    )
}
