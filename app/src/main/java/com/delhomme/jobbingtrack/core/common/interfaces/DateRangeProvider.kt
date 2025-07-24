package com.delhomme.jobbingtrack.core.common.interfaces

import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.flow.Flow


/**
 * Fournit une requête générique "BETWEEN :start AND :end" pour tout DAO Room.
 * Chaque DAO qui l'implémente doit préciser `tableName` et `dateColumn`.
 */
interface DateRangeProvider<T> {
    val tableName: String
    val dateColumn: String

    @RawQuery
    fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<T>>

    fun getByDateRangeForUser(userId: String, from: Long, to: Long): Flow<List<T>> {
        val query = SimpleSQLiteQuery(
            """
            SELECT * FROM $tableName 
            WHERE userId = '$userId' 
            AND $dateColumn BETWEEN $from AND $to 
            ORDER BY $dateColumn DESC
            """
        )
        return getByDateRange(query)
    }
}