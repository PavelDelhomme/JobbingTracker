package com.delhomme.jobbingtrack.calls

import androidx.room.Entity
import androidx.room.Index


@Entity(
    primaryKeys = ["callId", "contactId"],
    indices = [Index("contactId")]
)
data class CallWithContactsCrossRef(
    val callId: String,
    val contactId: String
)
