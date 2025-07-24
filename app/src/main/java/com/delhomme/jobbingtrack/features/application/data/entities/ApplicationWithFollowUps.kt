package com.delhomme.jobbingtrack.features.application.data.entities
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity

data class ApplicationWithFollowUps(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationFollowUpCrossRef::class,
            parentColumn = "application_id",
            entityColumn = "follow_up_id"
        )
    )
    val followUps: List<FollowUpEntity>
)