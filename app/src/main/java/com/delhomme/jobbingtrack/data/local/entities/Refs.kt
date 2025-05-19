package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.Index

// 1) Candidature <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["candidatureId", "contactId"],
    indices = [ Index("contactId") ]
)
data class CandidatureContactCrossRef(
    val candidatureId: String,
    val contactId: String
)

// 2) Entreprise <-> Candidature (n-à-n)
@Entity(
    primaryKeys = ["entrepriseId", "candidatureId"],
    indices = [ Index("candidatureId") ]
)
data class EntrepriseCandidatureCrossRef(
    val entrepriseId: String,
    val candidatureId: String
)

// 3) Entretien <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["entretienId", "contactId"],
    indices = [ Index("contactId") ]
)
data class EntretienContactCrossRef(
    val entretienId: String,
    val contactId: String
)