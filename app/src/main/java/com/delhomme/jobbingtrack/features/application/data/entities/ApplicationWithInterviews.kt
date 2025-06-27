package com.delhomme.jobbingtrack.features.application.data.entities
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import java.util.UUID


data class ApplicationWithInterviews(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ApplicationInterviewCrossRef::class,
            parentColumn = "applicationId",
            entityColumn = "interviewId"
        )
    )
    val interviews: List<InterviewEntity>
)
