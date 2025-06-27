package com.delhomme.jobbingtrack.feature.call.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class CallViewModel @Inject constructor(
    private val repo: CallRepository
) : ViewModel() {
    /** Toutes les relances pour cet utilisateur */
    fun callsForUser(userId: String): LiveData<List<CallEntity>> =
        repo.allForUser(userId).asLiveData()

    fun archivedForUser(userId: String): LiveData<List<CallEntity>> =
        repo.archivedForUser(userId).asLiveData()

    /** 1) Toutes les candidatures pour un user */
    fun allForUser(userId: String): LiveData<List<CallEntity>> =
        repo.allForUser(userId).asLiveData()

    /** Détaillé par id */
    fun callById(id: String, userId: String): LiveData<CallEntity?> =
        repo.byId(id, userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<CallEntity>> =
        repo.activeForUser(userId).asLiveData()

    fun save(appel: CallEntity) = viewModelScope.launch { repo.save(appel) }
    fun update(appel: CallEntity) = viewModelScope.launch { repo.update(appel) }
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    fun callsBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<CallEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}
