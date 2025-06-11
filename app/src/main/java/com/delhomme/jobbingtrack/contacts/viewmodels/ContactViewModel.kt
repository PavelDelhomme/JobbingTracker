package com.delhomme.jobbingtrack.contacts.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import com.delhomme.jobbingtrack.contacts.entities.DepartmentTypeEntity
import com.delhomme.jobbingtrack.contacts.entities.PositionTypeEntity
import com.delhomme.jobbingtrack.contacts.repositories.ContactRepository
import com.delhomme.jobbingtrack.contacts.repositories.DepartmentTypeRepository
import com.delhomme.jobbingtrack.contacts.repositories.PositionTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repo: ContactRepository
) : ViewModel() {

    /** Toutes les contacts pour cet utilisateur */
    fun allForUser(userId: String): LiveData<List<ContactEntity>> =
        repo.allForUser(userId).asLiveData()

    /** Détaillé par id */
    fun contactById(id: String, userId: String): LiveData<ContactEntity?> =
        repo.byId(id, userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<ContactEntity>> =
        repo.activeForUser(userId).asLiveData()

    fun archivedForUser(userId: String): LiveData<List<ContactEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun save(contact: ContactEntity) = viewModelScope.launch { repo.save(contact) }
    fun update(contact: ContactEntity) = viewModelScope.launch { repo.update(contact) }
    fun archive(ids: List<String>, userId: String)      = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String)       = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String)      = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String)= viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String)                        = viewModelScope.launch { repo.deleteAll(userId) }

}

@HiltViewModel
class DepartmentTypeViewModel @Inject constructor(
    private val repo: DepartmentTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: DepartmentTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: DepartmentTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}



@HiltViewModel
class PositionTypeViewModel @Inject constructor(
    private val repo: PositionTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: PositionTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: PositionTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
