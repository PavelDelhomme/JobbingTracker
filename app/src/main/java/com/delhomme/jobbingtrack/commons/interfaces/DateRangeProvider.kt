package com.delhomme.jobbingtrack.commons.interfaces


import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.flow.Flow


/**
 * Fournit une requête générique "BETWEEN :start AND :end" pour tout DAO Room.
 * Chaque DAO qui l'implémente doit préciser `tableName` et `dateColumn`.
 */
interface DateRangeProvider<T> {
    val tableName: String
    val dateColumn: String

    fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<T>>

    fun getByDateRangeForUser(userId: String, start: Long, end: Long): Flow<List<T>> {
        val sql = """
      SELECT * 
        FROM $tableName
       WHERE userId = ?
         AND $dateColumn BETWEEN ? AND ?
       ORDER BY $dateColumn
    """.trimIndent()
        return getByDateRange(
            SimpleSQLiteQuery(sql, arrayOf(userId, start, end))
        )
    }
}