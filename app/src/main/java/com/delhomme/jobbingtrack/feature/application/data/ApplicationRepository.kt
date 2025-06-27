package com.delhomme.jobbingtrack.feature.application.data
/*
import com.delhomme.jobbingtrack.core.repository.BaseRepository
import com.delhomme.jobbingtrack.feature.application.domain.Application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApplicationRepository @Inject constructor(
    private val dao: ApplicationDao
) : BaseRepository<ApplicationEntity>(dao) {
    // Méthodes spécifiques
    fun getApplications(userId: String): Flow<List<Application>> {
        return dao.getByUserId(userId).map { list ->
            list.map { it.toDomain() }
        }
    }
}
 */