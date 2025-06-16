package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyTypeEntity
import com.delhomme.jobbingtrack.datas.repositories.CompanyRepository
import com.delhomme.jobbingtrack.datas.repositories.CompanyTypeRepository
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


@HiltViewModel
class CompanyTypeViewModel @Inject constructor(
    private val repo: CompanyTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: CompanyTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CompanyTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
