package com.delhomme.jobbingtrack.sync

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SyncTracker(
    @PrimaryKey val id: String,
)