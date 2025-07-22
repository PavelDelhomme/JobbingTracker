package com.delhomme.jobbingtrack.core.common.entities

import com.delhomme.jobbingtrack.core.model.BaseEntity


data class CommonEntityFields(
    val userId: String,
    val syncHash: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val deletedAt: Long? = null,
    val archivedAt: Long? = null
) {
    // Méthode pour convertir CommonEntityFields en propriétés de BaseEntity
    fun applyToBaseEntity(entity: BaseEntity) {
        entity.apply {
            this.createdAt = this@CommonEntityFields.createdAt
            this.updatedAt = this@CommonEntityFields.updatedAt
            this.isDeleted = this@CommonEntityFields.isDeleted
            this.isArchived = this@CommonEntityFields.isArchived
            this.deletedAt = this@CommonEntityFields.deletedAt
            this.archivedAt = this@CommonEntityFields.archivedAt
            this.syncHash = this@CommonEntityFields.syncHash
            this.entityHash = this@CommonEntityFields.syncHash

        }
    }
}