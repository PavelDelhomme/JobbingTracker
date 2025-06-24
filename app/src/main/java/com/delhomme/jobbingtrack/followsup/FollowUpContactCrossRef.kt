package com.delhomme.jobbingtrack.followsup

import androidx.room.Entity
import androidx.room.Index
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.utils.Converters


@TypeConverters(Converters::class)
@Entity(
    primaryKeys = ["followUpId", "contactId"],
    indices = [ Index("contactId") ]
)
data class FollowUpContactCrossRef(
    val followUpId: String,
    val contactId: String
)
