package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.HasId

fun <T : HasId> getLabelById(id: String?, items: List<T>, getLabel: (T) -> String): String {
    return items.find { it.id == id }?.let { getLabel(it) } ?: ""
}

fun <T : HasId> getEntityById(id: String?, items: List<T>): T? {
    return items.find { it.id == id }
}

fun extractIdFromFormattedString(value: String): String {
    return value.substringAfterLast("(").removeSuffix(")").trim()
}

enum class DialogType { ARCHIVE, DELETE }
