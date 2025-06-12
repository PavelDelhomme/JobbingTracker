package com.delhomme.jobbingtrack.sync

import androidx.room.Entity

@Entity
data class SyncTracker(
    @PrimaryKey val id: String,
)