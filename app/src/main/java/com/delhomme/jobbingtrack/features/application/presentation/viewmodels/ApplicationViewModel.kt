package com.delhomme.jobbingtrack.features.application.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationStatusEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationTypeEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ContractTypeEntity
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationPlatformRepository
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationRepository
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationStatusRepository
import com.delhomme.jobbingtrack.features.application.data.repositories.ApplicationTypeRepository
import com.delhomme.jobbingtrack.features.application.data.repositories.ContractTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val repo: ApplicationRepository,
    private val typeRepository: ApplicationTypeRepository,
    private val platformRepository: ApplicationPlatformRepository,
    private val statusRepository: ApplicationStatusRepository,
    private val contractTypeRepository: ContractTypeRepository,
) : ViewModel() {

    val allTypes: LiveData<List<ApplicationTypeEntity>> = typeRepository.all.asLiveData()
    val allPlatforms: LiveData<List<ApplicationPlatformEntity>> = platformRepository.all.asLiveData()
    val allStatuses: LiveData<List<ApplicationStatusEntity>> = statusRepository.all.asLiveData()
    val allContractTypes: LiveData<List<ContractTypeEntity>> = contractTypeRepository.all.asLiveData()

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

