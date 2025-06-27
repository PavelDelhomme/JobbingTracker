package com.delhomme.jobbingtrack.feature.company.domain.model


data class Company(
    // Data d'héritage de HasIdProvider
    override val id: String,
    val userId: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,

    // Data propre
    var name: String,
    var type: String?,
    var phone: String?,
    var email: String?,
    var hrEmail: String?,
    var address: String?,
    var notes: String?,
    val contactsIds: List<String>?,
    val followUpsIds: List<String>?,
    val applicationsIds: List<String>?,
    val interviewsIds: List<String>?,
    val callsIds: List<String>?,
) : HasIdProvider



data class Company(
    // Data d'héritage de HasIdProvider
    override val id: String,
    val userId: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,

    // Data propre
    var name: String,
    var type: String?,
    var phone: String?,
    var email: String?,
    var hrEmail: String?,
    var address: String?,
    var notes: String?,
    val contactsIds: List<String>?,
    val followUpsIds: List<String>?,
    val applicationsIds: List<String>?,
    val interviewsIds: List<String>?,
    val callsIds: List<String>?,
) : HasIdProvider