package com.delhomme.jobbingtrack.ui.components.forms.selectors

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import com.delhomme.jobbingtrack.data.HasId
import kotlin.collections.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : HasId> EntitySelectorField(
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
