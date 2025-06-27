package com.delhomme.jobbingtrack.core.utils

import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider


fun <T : HasIdProvider> getLabelById(id: String?, items: List<T>, getLabel: (T) -> String): String {
    return items.find { it.id == id }?.let { getLabel(it) } ?: "Non spécifié"
}

fun <T : HasIdProvider> getEntityById(id: String?, items: List<T>): T? {
    return items.find { it.id == id }
}

fun extractIdFromFormattedString(value: String): String {
    return value.substringAfterLast("(").removeSuffix(")").trim()
}

enum class DialogType { ARCHIVE, DELETE }
