package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.dao.CandidatureDao
import com.delhomme.jobbingtrack.data.local.repository.CandidatureRepository
import com.delhomme.jobbingtrack.data.local.repository.EntrepriseRepository
import com.delhomme.jobbingtrack.data.local.repository.EntretienRepository
import com.delhomme.jobbingtrack.data.local.repository.AppelRepository
import com.delhomme.jobbingtrack.data.local.repository.RelanceRepository
import com.delhomme.jobbingtrack.data.local.repository.ContactRepository
import com.delhomme.jobbingtrack.data.local.repository.UserRepository
import com.delhomme.jobbingtrack.data.local.repository.ProfileRepository
import com.delhomme.jobbingtrack.utils.countByDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class DashboardViewModel(app: Application): AndroidViewModel(app) {
    private val db = JobbingTrackApp.database
    private val CandidatureDao = db.candidatureDao()
    private val EntrepriseDao = db.entrepriseDao()
    private val EntretienDao = db.entretienDao()
    private val AppelDao = db.appelDao()
    private val RelanceDao = db.relanceDao()
    private val ContactDao = db.contactDao()
    private val UserDao = db.userDao()
    private val ProfileDao = db.profileDao()

    private val repoCandidature = CandidatureRepository(CandidatureDao)
    private val repoEntreprise = EntrepriseRepository(EntrepriseDao)
    private val repoEntretien = EntretienRepository(EntretienDao)
    private val repoAppel = AppelRepository(AppelDao)
    private val repoRelance = RelanceRepository(RelanceDao)
    private val repoContact = ContactRepository(ContactDao)
    private val repoUser = UserRepository(UserDao)
    private val repoProfile = ProfileRepository(ProfileDao)

    // Plage de dates choisie
    private val _startDate = MutableStateFlow(Instant.now().minus(7, ChronoUnit.DAYS))
    private val _endDate = MutableStateFlow(Instant.now())
    val startDate: StateFlow<Instant> = _startDate
    val endDate: StateFlow<Instant> = _endDate

    // Agrégation générique
    private fun <E> StateFlow<List<E>>.perDay(
        dateSelector: (E) -> Instant
    ): StateFlow<List<Pair<LocalDate, Int>>> =
        combine(_startDate, _endDate, this) { start, end, list ->
            list.countByDay(dateSelector, start, end)
        }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Flux exposés
    val candsPerDay     = repoCandidature.candidatures.perDay { Instant.ofEpochMilli(it.applicationDate)}
    val relsPerDay      = repoRelance.relances
    val appelsPerDay    = repoAppel.appels
    val entretiensPerDay= repoEntretien.entretiensWithContacts
        .perDay { it.entretien.dateTime }

}