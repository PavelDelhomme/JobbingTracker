package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableForm(
    fields: List<FormField>,
    initialValues: Map<String, String>? = null,
    onSubmit: (Map<String, String>) -> Unit
) {
    val fieldValues = remember { mutableStateMapOf<String, String>() }

    // Préremplir les valeurs initiales
    LaunchedEffect(Unit) {
        fields.forEach { field ->
            val value = initialValues?.get(field.name) ?: field.initialValue ?: ""
            fieldValues[field.name] = value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        fields.forEach { field ->
            when (field.type) {
                FieldType.TEXT, FieldType.EMAIL, FieldType.PHONE, FieldType.NUMBER, -> {
                    OutlinedTextField(
                        value = fieldValues[field.name] ?: "",
                        onValueChange = { if (!field.readOnly) fieldValues[field.name] = it },
                        label = { Text(field.label) },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = field.readOnly,
                        keyboardOptions = when (field.type) {
                            FieldType.NUMBER -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            FieldType.EMAIL -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                            FieldType.PHONE -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                            else -> KeyboardOptions.Default
                        }
                    )
                }
                FieldType.MULTILINE_TEXT -> {
                    OutlinedTextField(
                        value = fieldValues[field.name] ?: "",
                        onValueChange = { if (!field.readOnly) fieldValues[field.name] = it },
                        label = { Text(field.label) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        readOnly = field.readOnly,
                        maxLines = 5
                    )
                }
                FieldType.BOOLEAN, FieldType.CHECKBOX -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(field.label)
                        Switch(
                            checked = fieldValues[field.name]?.toBoolean() ?: false,
                            onCheckedChange = { if (!field.readOnly) fieldValues[field.name] = it.toString() },
                            enabled = !field.readOnly
                        )
                    }
                }
                FieldType.DATE, FieldType.TIME -> {
                    // TODO plus tard : date picker / dropdown
                    OutlinedTextField(
                        value = fieldValues[field.name] ?: "",
                        onValueChange = { fieldValues[field.name] = it },
                        label = { Text(field.label) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                FieldType.DROPDOWN -> {
                    var showAddDialog by remember { mutableStateOf(false) }
                    var newOptionText by remember { mutableStateOf("") }

                    val options = remember { mutableStateListOf<String>().apply { addAll(field.options ?: emptyList()) } }
                    var expanded by remember { mutableStateOf(false) }

                    Column {
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            OutlinedTextField(
                                value = fieldValues[field.name] ?: "",
                                onValueChange = { fieldValues[field.name] = it },
                                label = { Text(field.label) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                readOnly = true
                            )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                options.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option) },
                                        onClick = {
                                            fieldValues[field.name] = option
                                            expanded = false
                                        }
                                    )
                                }
                                DropdownMenuItem(
                                    text = { Text("➕ Ajouter une nouvelle...") },
                                    onClick = {
                                        expanded = false
                                        showAddDialog = true
                                    }
                                )
                            }
                        }

                        if (showAddDialog) {
                            AlertDialog(
                                onDismissRequest = { showAddDialog = false },
                                confirmButton = {
                                    TextButton(onClick = {
                                        if (newOptionText.isNotBlank()) {
                                            options.add(newOptionText)
                                            fieldValues[field.name] = newOptionText
                                        }
                                        newOptionText = ""
                                        showAddDialog = false
                                    }) {
                                        Text("Ajouter")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = {
                                        newOptionText = ""
                                        showAddDialog = false
                                    }) {
                                        Text("Annuler")
                                    }
                                },
                                title = { Text("Ajouter une nouvelle option") },
                                text = {
                                    OutlinedTextField(
                                        value = newOptionText,
                                        onValueChange = { newOptionText = it },
                                        label = { Text("Nouvelle valeur") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            )
                        }
                    }
                }

                FieldType.SUGGESTION_TEXT -> {
                    var expanded by remember { mutableStateOf(false) }
                    val input = fieldValues[field.name] ?: ""
                    val suggestions = field.options?.filter { it.contains(input, ignoreCase = true) } ?: emptyList()

                    Column {
                        OutlinedTextField(
                            value = input,
                            onValueChange = {
                                if (!field.readOnly) fieldValues[field.name] = it
                                expanded = true
                            },
                            label = { Text(field.label) },
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (expanded && suggestions.isNotEmpty()) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                suggestions.forEach { suggestion ->
                                    DropdownMenuItem(
                                        text = { Text(suggestion) },
                                        onClick = {
                                            fieldValues[field.name] = suggestion
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSubmit(fieldValues) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enregistrer")
        }
    }
}
