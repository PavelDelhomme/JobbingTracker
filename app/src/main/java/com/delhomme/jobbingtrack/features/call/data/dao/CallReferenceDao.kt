package com.delhomme.jobbingtrack.features.call.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["callId", "contactId"],
    indices = [Index("contactId")]
)
data class CallWithContactsCrossRef(
    @ColumnInfo(name = "callId") val callId: String,
    @ColumnInfo(name = "contactId") val contactId: String
)