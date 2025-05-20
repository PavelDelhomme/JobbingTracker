package com.delhomme.jobbingtrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delhomme.jobbingtrack.data.local.dao.AppelDao
import com.delhomme.jobbingtrack.data.local.dao.CandidatureDao
import com.delhomme.jobbingtrack.data.local.dao.ContactDao
import com.delhomme.jobbingtrack.data.local.dao.EventDao
import com.delhomme.jobbingtrack.data.local.dao.EntrepriseDao
import com.delhomme.jobbingtrack.data.local.dao.EntretienDao
import com.delhomme.jobbingtrack.data.local.dao.ProfileDao
import com.delhomme.jobbingtrack.data.local.dao.RelanceDao
import com.delhomme.jobbingtrack.data.local.dao.UserDao
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.ProfileEntity
import com.delhomme.jobbingtrack.data.local.entities.UserEntity

import com.delhomme.jobbingtrack.data.local.entities.CandidatureContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntretienContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseCandidatureCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity


@Database(
    entities = [
        AppelEntity::class,
        CandidatureEntity::class,
        ContactEntity::class,
        EntrepriseEntity::class,
        EntretienEntity::class,
        RelanceEntity::class,
        ProfileEntity::class,
        UserEntity::class,
        CandidatureContactCrossRef::class,
        EntretienContactCrossRef::class,
        EntrepriseCandidatureCrossRef::class,
        EventEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appelDao(): AppelDao
    abstract fun candidatureDao(): CandidatureDao
    abstract fun contactDao(): ContactDao
    abstract fun entrepriseDao(): EntrepriseDao
    abstract fun entretienDao(): EntretienDao
    abstract fun relanceDao(): RelanceDao
    abstract fun profileDao(): ProfileDao
    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao

}