package com.delhomme.jobbingtrack.features.call.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["call_id", "contact_id"],
    indices = [Index("contact_id")]
)
data class CallWithContactsCrossRef(
    @ColumnInfo(name = "call_id") val callId: String,
    @ColumnInfo(name = "contact_id") val contactId: String
)