package com.delhomme.jobbingtrack.data.forms

import androidx.compose.runtime.mutableStateListOf


object FormSuggestions {

    val platforms = mutableStateListOf(
        "LinkedIn", "Indeed", "Welcome to the Jungle", "HelloWork", "Pôle Emploi"
    )

    val contractTypes = mutableStateListOf(
        "CDI", "CDD", "Alternance", "Stage", "Freelance"
    )

    val jobTitles = mutableStateListOf(
        "Développeur", "Designer", "Chef de Projet", "Data Analyst", "DevOps"
    )

    val contactPositions = mutableListOf(
        "Manager", "Recruteur", "CTO", "Chargé RH", "CEO"
    )

    val relanceTypes = mutableListOf(
        "Appel", "Email", "Sur place"
    )

    val entretienTypes = mutableListOf(
        "RH", "TECHNICAL"
    )

    val entretienStyles = mutableListOf(
        "ON_SITE", "REMOTE"
    )
}