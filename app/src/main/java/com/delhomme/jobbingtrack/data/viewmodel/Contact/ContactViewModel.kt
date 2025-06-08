package com.delhomme.jobbingtrack.data.viewmodel.Contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity
import com.delhomme.jobbingtrack.data.local.repository.contact.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ContactRepository(JobbingTrackApp.Companion.database.contactDao())

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