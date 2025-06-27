package com.delhomme.jobbingtrack.etc.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String =
        value.joinToString(separator = ";")

    @TypeConverter
    fun toStringList(value: String): List<String> =
        if (value.isEmpty()) emptyList() else value.split(";")
}
