package com.delhomme.jobbingtrack.utils

inline fun <reified T : Enum<T>> safeEnumValueOf(input: String?): T? {
    return input?.let {
        enumValues<T>().firstOrNull { enum -> enum.name.equals(it, ignoreCase = true) }
    }
}
