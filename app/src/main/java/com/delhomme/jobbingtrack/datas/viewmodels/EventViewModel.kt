package com.delhomme.jobbingtrack.datas.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.datas.entities.events.EventEntity
import com.delhomme.jobbingtrack.datas.entities.events.EventTypeEntity
import com.delhomme.jobbingtrack.datas.repositories.EventRepository
import com.delhomme.jobbingtrack.datas.repositories.EventTypeRepository
import kotlinx.coroutines.launch


class EventViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EventRepository(JobbingTrackApp.database.eventDao())

    /** LiveData exposant la liste des événements */
    fun eventsForUser(userId: String): LiveData<List<EventEntity>> = repo.allForUser(userId).asLiveData()

    /** LiveData exposant un seul événement */
    fun eventById(id: String, userId: String): LiveData<EventEntity?> = repo.byId(id, userId).asLiveData()

    /** Sauvegarde (insert ou update) */
    fun save(event: EventEntity) = viewModelScope.launch { repo.save(event) }

    /** Mise à jour */
    fun update(event: EventEntity) = viewModelScope.launch { repo.update(event) }

    /** Archive */
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }

    /** Supprime “logiquement” */
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }

    /** Restaure */
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }

    /** Supprime permanentement */
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }

    /** Efface tout */
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    fun eventsBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<EventEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}



class EventTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EventTypeRepository(JobbingTrackApp.database.eventTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: EventTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EventTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
