package com.delhomme.jobbingtrack.feature.company.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val repo: CompanyRepository
) : ViewModel() {

    fun allForUser(userId: String): LiveData<List<CompanyEntity>> = repo.allForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<CompanyEntity>> =
        repo.activeForUser(userId).asLiveData()

    /** 3) Archivées */
    fun archivedForUser(userId: String): LiveData<List<CompanyEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun companyById(id: String, userId: String): LiveData<CompanyEntity?> = repo.byId(id, userId).asLiveData()

    fun save(company: CompanyEntity) = viewModelScope.launch { repo.save(company) }

    fun update(company: CompanyEntity) = viewModelScope.launch { repo.update(company) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
}
