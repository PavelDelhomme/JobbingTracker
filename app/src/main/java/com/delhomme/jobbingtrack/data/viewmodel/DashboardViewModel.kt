package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.delhomme.jobbingtrack.data.local.repository.*
import com.delhomme.jobbingtrack.utils.countByDay
import kotlinx.coroutines.flow.*
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import com.delhomme.jobbingtrack.utils.*

class DashboardViewModel(app: Application, private val userId: String): AndroidViewModel(app) {

    private val db = JobbingTrackApp.database

    private val repoCandidature = CandidatureRepository(db.candidatureDao())
    private val repoEntreprise = EntrepriseRepository(db.entrepriseDao())
    private val repoEntretien = EntretienRepository(db.entretienDao())
    private val repoAppel = AppelRepository(db.appelDao())
    private val repoRelance = RelanceRepository(db.relanceDao())
    private val repoContact = ContactRepository(db.contactDao())
    private val repoUser = UserRepository(db.userDao())
    private val repoProfile = ProfileRepository(db.profileDao())

    // Plage de dates choisie
    private val _startDate = MutableStateFlow(Instant.now().minus(7, ChronoUnit.DAYS))
    private val _endDate = MutableStateFlow(Instant.now())

    /** Flux bruts exposés en StateFlow pour chaque entité */
    val candidaturesFlow = repoCandidature
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val relancesFlow = repoRelance
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val appelsFlow = repoAppel
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val entretiensFlow = repoEntretien
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val entreprisesFlow = repoEntreprise
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val contactsFlow = repoContact
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    /** Extension générique pour compter par jour */
    private fun <E> StateFlow<List<E>>.perDay(dateSelector: (E) -> Instant):
            StateFlow<List<Pair<LocalDate, Int>>> =
        combine(_startDate, _endDate, this) { start, end, list ->
            list.countByDay(dateSelector, start, end)
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    /** Exposition des datas agrégées */
    val candsPerDay = candidaturesFlow.perDay(CandidatureEntity::toInstant)
    val relsPerDay  = relancesFlow.perDay(RelanceEntity::toInstant)
    val appelsPerDay= appelsFlow.perDay(AppelEntity::toInstant)
    val entretiensPerDay = entretiensFlow.perDay(EntretienEntity::toInstant)

    /** Si vous avez besoin de changer la plage depuis l’UI */
    fun setDateRange(from: Instant, to: Instant) {
        _startDate.value = from
        _endDate.value   = to
    }
}