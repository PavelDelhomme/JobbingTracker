package com.delhomme.jobbingtrack.data.local.repository


import com.delhomme.jobbingtrack.data.local.dao.EntretienDao
import com.delhomme.jobbingtrack.data.local.entities.EntretienContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import kotlinx.coroutines.flow.Flow

class EntretienRepository(private val dao: EntretienDao) {
    val entretiensWithContacts: Flow<List<EntretienWithContacts>> = dao.getAllWithContacts()

    val entretiens: Flow<List<EntretienEntity>> = dao.getAll()

    suspend fun save(entretien: EntretienEntity, contactIds: List<String>) {
        dao.insert(entretien)
        dao.clearContactsFor(entretien.id)
        contactIds.forEach {
            dao.insertCrossRef(EntretienContactCrossRef(entretien.id, it))
        }
    }

    suspend fun archive(id: String)             = dao.archive(id)
    suspend fun delete(id: String)              = dao.delete(id)
}