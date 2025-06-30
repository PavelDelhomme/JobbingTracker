package com.delhomme.jobbingtrack.features.profil.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.features.user.data.entities.UserEntity

data class ProfilWithRelations(
    @Embedded val profile: ProfilEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: UserEntity?
)
