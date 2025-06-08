package com.delhomme.jobbingtrack.dashboard.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.utils.countByDay
import com.delhomme.jobbingtrack.utils.toInstant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class DashboardViewModel(app: Application, private val userId: String): AndroidViewModel(app) {

    private val db = JobbingTrackApp.database

    private val repoApplication = ApplicationRepository(db.applicationDao())
    private val repoCompany = CompanyRepository(db.companyDao())
    private val repoInterview = InterviewRepository(db.interviewDao())
    private val repoCall = CallRepository(db.callDao())
    private val repoFollowUp = FollowUpRepository(db.followUpDao())
    private val repoContact = ContactRepository(db.contactDao())
    private val repoUser = UserRepository(db.userDao())
    private val repoProfile = ProfilRepository(db.profileDao())

    // Plage de dates choisie
    private val _startDate = MutableStateFlow(Instant.now().minus(7, ChronoUnit.DAYS))
    private val _endDate = MutableStateFlow(Instant.now())

    /** Flux bruts exposés en StateFlow pour chaque entité */
    val applicationsFlow = repoApplication
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val followUpFlow = repoFollowUp
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val callsFlow = repoCall
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val interviewsFlow = repoInterview
        .allForUser(userId)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val companiesFlow = repoCompany
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
    val applicationsPerDay = applicationsFlow.perDay(ApplicationEntity::toInstant)
    val followUpsPerDay  = followUpFlow.perDay(FollowUpEntity::toInstant)
    val callsPerDay = callsFlow.perDay(CallEntity::toInstant)
    val interviewsPerDay = interviewsFlow.perDay { it.interview.toInstant() }

    /** Si vous avez besoin de changer la plage depuis l’UI */
    fun setDateRange(from: Instant, to: Instant) {
        _startDate.value = from
        _endDate.value   = to
    }
}