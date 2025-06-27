package com.delhomme.jobbingtrack.etc.bad


/**
 * Met à jour la liste d'IDs en ajoutant ou en retirant un ID, sans doublon.
 */
fun updateIdList(
    current: List<String>?,
    id: String?,
    add: Boolean
): List<String> {
    val list = (current ?: emptyList()).toMutableList()
    if (id.isNullOrBlank()) return list
    if (add) {
        if (!list.contains(id)) list.add(id)
    } else {
        list.remove(id)
    }
    return list
}

/**
 * Logique générique pour mettre à jour les liens d'une entité vers une CompanyEntity
 * Peut être utilisé pour Contact, Application, Call, Interview, FollowUp, etc.
 */
fun <T> handleCompanyChange(
    oldCompany: T?,
    newCompany: T?,
    entityId: String?,
    companyIdField: (T) -> List<String>?,
    copyWithIds: (T, List<String>) -> T,
    save: (T) -> Unit
) {
    // Retire l'entité de l'ancienne entreprise
    oldCompany?.let {
        val updated = updateIdList(companyIdField(it), entityId, false)
        save(copyWithIds(it, updated))
    }
    // Ajoute l'entité à la nouvelle entreprise
    newCompany?.let {
        val updated = updateIdList(companyIdField(it), entityId, true)
        save(copyWithIds(it, updated))
    }
}