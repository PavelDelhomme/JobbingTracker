package com.delhomme.jobbingtrack.core.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.util.UUID

abstract class BaseEntity {
    @PrimaryKey
    abstract val id: String

    // Champ utilisateur obligatoire pour la synchronisation
    @ColumnInfo(name = "user_id")
    abstract val userId: String

    // Champs de métadonnées temporelles
    @ColumnInfo(name = "created_at")
    var createdAt: Long = System.currentTimeMillis()

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = System.currentTimeMillis()

    @ColumnInfo(name = "last_sync_at")
    var lastSyncAt: Long? = null

    // Champs d'état
    @ColumnInfo(name = "is_deleted")
    var isDeleted: Boolean = false

    @ColumnInfo(name = "is_archived")
    var isArchived: Boolean = false

    @ColumnInfo(name = "deleted_at")
    var deletedAt: Long? = null

    @ColumnInfo(name = "archived_at")
    var archivedAt: Long? = null

    // Champ de hachage pour détecter les modifications
    @ColumnInfo(name = "entity_hash")
    var entityHash: String = ""

    // Champ de hachage de synchronisation (maintenu pour compatibilité)
    @ColumnInfo(name = "sync_hash")
    var syncHash: String = ""

    // Génère un hash basé sur le contenu de l'entité
    fun calculateHash(): String {
        val gson = Gson()
        val jsonString = gson.toJson(this)
        return UUID.nameUUIDFromBytes(jsonString.toByteArray()).toString()
    }

    // Met à jour le hash de l'entité
    fun updateEntityHash() {
        entityHash = calculateHash()
        updatedAt = System.currentTimeMillis()
    }

    // Vérifie si l'entité a été modifiée depuis la dernière synchronisation
    fun hasChanged(): Boolean {
        val currentHash = calculateHash()
        return currentHash != entityHash
    }

    // Marque l'entité comme supprimée
    fun markAsDeleted() {
        isDeleted = true
        deletedAt = System.currentTimeMillis()
        updatedAt = System.currentTimeMillis()
    }

    // Marque l'entité comme archivée
    fun markAsArchived() {
        isArchived = true
        archivedAt = System.currentTimeMillis()
        updatedAt = System.currentTimeMillis()
    }

    // Restaure l'entité (annule la suppression)
    fun restore() {
        isDeleted = false
        deletedAt = null
        updatedAt = System.currentTimeMillis()
    }

    // Désarchive l'entité
    fun unarchive() {
        isArchived = false
        archivedAt = null
        updatedAt = System.currentTimeMillis()
    }
}