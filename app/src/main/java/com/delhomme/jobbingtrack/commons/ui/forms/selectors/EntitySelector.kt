package com.delhomme.jobbingtrack.commons.ui.forms.selectors

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : HasIdProvider> EntitySelectorField(
    label: String,
    selectedEntityId: String?,
    allEntities: List<T>,
    getEntityLabel: (T) -> String,
    onEntitySelected: (T) -> Unit,
    allowCreation: Boolean = false,
    onCreateEntity: ((String) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }

    val selectedEntity = allEntities.find { it.id == selectedEntityId }
    val suggestions = allEntities.filter { getEntityLabel(it).contains(inputText, ignoreCase = true) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(
            value = selectedEntity?.let { "${getEntityLabel(it)} (${it.id})" } ?: inputText,
            onValueChange = { inputText = it },
            label = { Text(label) },
            readOnly = false,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            suggestions.forEach { entity ->
                DropdownMenuItem(
                    text = { Text("${getEntityLabel(entity)} (${entity.id})") },
                    onClick = {
                        onEntitySelected(entity)
                        inputText = "${getEntityLabel(entity)} (${entity.id})"
                        expanded = false
                    }
                )
            }

            if (allowCreation && inputText.isNotBlank()) {
                DropdownMenuItem(
                    text = { Text("Créer \"$inputText\"") },
                    onClick = {
                        onCreateEntity?.invoke(inputText)
                        expanded = false
                    }
                )
            }
        }
    }
}
