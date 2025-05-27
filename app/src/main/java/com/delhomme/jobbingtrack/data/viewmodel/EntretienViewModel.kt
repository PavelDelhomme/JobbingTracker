package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.local.repository.EntretienRepository
import com.delhomme.jobbingtrack.utils.mappers.toDomain as toEntretienDomain
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EntretienViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EntretienRepository(JobbingTrackApp.database.entretienDao())

    fun allForUser(userId: String): LiveData<List<EntretienWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<Entretien>> = repo.activeForUser(userId)

    fun entretienById(id: String, userId: String): LiveData<EntretienWithContacts?> = repo.byIdWithContacts(id, userId).asLiveData()

    fun save(entretien: EntretienEntity, contactsIds: List<String>) = viewModelScope.launch { repo.save(entretien, contactsIds) }

    fun archivedForUser(userId: String): LiveData<List<EntretienEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun getAllWithContacts(userId: String): LiveData<List<EntretienWithContacts>> = repo.withContactsForUser(userId).asLiveData()
    fun activeWithContactsForUser(userId: String): LiveData<List<EntretienWithContacts>> {
        return repo.getActiveWithContacts(userId)
    }

    /*fun activeForUserWithContacts(userId: String): LiveData<List<Entretien>> =
        repo.withContactsForUser(userId)
            .map { list -> list.map { it.toEntretienDomain() } }
            .asLiveData()
    */

    fun update(entretien: EntretienEntity) = viewModelScope.launch { repo.update(entretien) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun archiveOne(id: String, userId: String) = archive(listOf(id), userId)

    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun deleteOne(id: String, userId: String) = delete(listOf(id), userId)

    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun restoreOne(id: String, userId: String) = restore(listOf(id), userId)

    fun isDeletedForUser(userId: String): LiveData<List<EntretienEntity>> =
        repo.deletedForUser(userId).asLiveData()

    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun deleteForeverOne(id: String, userId: String) = deleteForever(listOf(id), userId)

    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    fun entretiensBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<EntretienEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}