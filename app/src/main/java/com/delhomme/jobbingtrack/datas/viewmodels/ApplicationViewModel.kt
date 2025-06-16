package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationStatusEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.datas.repositories.ApplicationPlatformRepository
import com.delhomme.jobbingtrack.datas.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.datas.repositories.ApplicationStatusRepository
import com.delhomme.jobbingtrack.datas.repositories.ApplicationTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val repo: ApplicationRepository
) : ViewModel() {

    /** 1) Toutes les candidatures pour un user */
    fun allForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.allForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.activeForUser(userId).asLiveData()

    /** 3) Archivées */
    fun archivedForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.archivedForUser(userId).asLiveData()

    /** 4) Supprimées (corbeille) */
    fun deletedForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.deletedForUser(userId).asLiveData()

    /** 5) Détail par id */
    fun byId(id: String, userId: String): LiveData<ApplicationEntity?> =
        repo.byId(id, userId).asLiveData()

    fun save(cand: ApplicationEntity) = viewModelScope.launch { repo.save(cand) }

    fun update(cand: ApplicationEntity) = viewModelScope.launch { repo.update(cand) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }

    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }

    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }

    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    /** Plage de dates */
    fun between(userId: String, fromTs: Long, toTs: Long): LiveData<List<ApplicationEntity>> =
        repo.getByDateRange(userId, fromTs, toTs).asLiveData()
}



@HiltViewModel
class ApplicationTypeViewModel @Inject constructor(
    private val repo: ApplicationTypeRepository
) : ViewModel() {

    val all: LiveData<List<ApplicationTypeEntity>> = repo.all.asLiveData()

    fun getById(id: String): LiveData<ApplicationTypeEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ApplicationTypeEntity) = viewModelScope.launch {
        repo.save(entity)
    }

    fun delete(entity: ApplicationTypeEntity) = viewModelScope.launch {
        repo.delete(entity)
    }
}


@HiltViewModel
class ApplicationStatusViewModel @Inject constructor(
    private val repo: ApplicationStatusRepository
) : ViewModel() {
    val all: LiveData<List<ApplicationStatusEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ApplicationStatusEntity?> = repo.byId(id).asLiveData()
    fun save(status: ApplicationStatusEntity) = viewModelScope.launch { repo.save(status) }
    fun delete(status: ApplicationStatusEntity) = viewModelScope.launch { repo.delete(status) }
}

@HiltViewModel
class ApplicationPlatformViewModel @Inject constructor(
    private val repo: ApplicationPlatformRepository
) : ViewModel() {
    val all: LiveData<List<ApplicationPlatformEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ApplicationPlatformEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.delete(entity) }
}
