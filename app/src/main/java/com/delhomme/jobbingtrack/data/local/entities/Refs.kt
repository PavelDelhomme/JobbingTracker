package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.Entreprise

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

// 4) Entreprise <-> Contact (n-à-n)
@Entity(
    primaryKeys = ["entrepriseId", "contactId"],
    indices = [ Index("contactId") ]
)
data class EntrepriseContactCrossRef(
    val entrepriseId: String,
    val contactId: String
)

// 5) Entreprise <-> Relance (n-à-n)
@Entity(
    primaryKeys = ["entrepriseId", "relanceId"],
    indices = [ Index("relanceId") ]
)
data class EntrepriseRelanceCrossRef(
    val entrepriseId: String,
    val relanceId: String
)

// 6) Entreprise <-> Appel (n-à-n)
@Entity(
    primaryKeys = ["entrepriseId", "appelId"],
    indices = [ Index("appelId") ]
)
data class EntrepriseAppelCrossRef(
    val entrepriseId: String,
    val appelId: String
)

// 7) Entreprise <-> Entretien (n-à-n)
@Entity(
    primaryKeys = ["entrepriseId", "entretienId"],
    indices = [ Index("entretienId") ]
)
data class EntrepriseEntretienCrossRef(
    val entrepriseId: String,
    val entretienId: String
)
