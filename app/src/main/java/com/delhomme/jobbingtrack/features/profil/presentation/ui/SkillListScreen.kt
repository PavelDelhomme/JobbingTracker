package com.delhomme.jobbingtrack.features.profil.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun SkillListEditor(skills: List<String>, onAdd: (String) -> Unit, onRemove: (String) -> Unit, onModify: (String, String) -> Unit) {
    var newSkill by remember { mutableStateOf("") }
    Column {
        skills.forEach { Text("- $it") }
        Row {
            OutlinedTextField(value = newSkill, onValueChange = { newSkill = it }, label = { Text("Nouvelle compétence") })
            Button(onClick = {
                if (newSkill.isNotBlank()) {
                    onAdd(newSkill)
                    newSkill = ""
                }
            }) {
                Text("+")
            }
        }
    }
}


@Composable
fun SkillListEditor(skills: List<String>, onAdd: (String) -> Unit, onRemove: (String) -> Unit, onModify: (String, String) -> Unit) {
    var newSkill by remember { mutableStateOf("") }
    Column {
        skills.forEach { Text("- $it") }
        Row {
            OutlinedTextField(value = newSkill, onValueChange = { newSkill = it }, label = { Text("Nouvelle compétence") })
            Button(onClick = {
                if (newSkill.isNotBlank()) {
                    onAdd(newSkill)
                    newSkill = ""
                }
            }) {
                Text("+")
            }
        }
    }
}