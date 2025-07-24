package com.delhomme.jobbingtrack.core.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.util.UUID

abstract class BaseEntity {
    @PrimaryKey
    abstract val id: String

    // Champ utilisateur obligatoire pour la synchronisation
    @ColumnInfo(name = "userId")
    abstract var userId: String  // Renommé en camelCase

    // Champs de métadonnées temporelles
    @ColumnInfo(name = "createdAt")
    var createdAt: Long = System.currentTimeMillis()  // Renommé en camelCase

    @ColumnInfo(name = "updatedAt")
    var updatedAt: Long = System.currentTimeMillis()  // Renommé en camelCase

    // Champs d'état
    @ColumnInfo(name = "isDeleted")
    var isDeleted: Boolean = false  // Renommé en camelCase

    @ColumnInfo(name = "isArchived")
    var isArchived: Boolean = false  // Renommé en camelCase

    @ColumnInfo(name = "deletedAt")
    var deletedAt: Long? = null  // Renommé en camelCase

    @ColumnInfo(name = "archivedAt")
    var archivedAt: Long? = null  // Renommé en camelCase

    // Champ de hachage pour détecter les modifications
    @ColumnInfo(name = "entityHash")
    var entityHash: String = ""  // Renommé en camelCase

    // Champ de hachage de synchronisation (maintenu pour compatibilité)
    @ColumnInfo(name = "syncHash")
    var syncHash: String = ""  // Renommé en camelCase

    // Champ de hachage de dernière synchronisation (maintenu pour compatibilité)
    @ColumnInfo(name = "lastSyncHash")
    var lastSyncHash: String? = ""  // Renommé en camelCase

    @ColumnInfo(name = "lastSyncAt")
    var lastSyncAt: Long? = null  // Modifié en Long et renommé en camelCase

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