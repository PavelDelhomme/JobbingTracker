package com.delhomme.jobbingtrack.ui.components

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

    val followUpTypes = mutableListOf(
        "Appel", "Email", "Sur place"
    )

    val interviewTypes = mutableListOf(
        "RH", "TECHNICAL"
    )

    val interviewStyles = mutableListOf(
        "ON_SITE", "REMOTE"
    )
}