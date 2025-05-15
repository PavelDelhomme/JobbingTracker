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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun rememberFormattedDateTime(millis: Long?): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return millis?.let { sdf.format(Date(it)) } ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernDateTimePickerField(
    label: String,
    initialMillis: Long? = null,
    onDateTimeSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var displayMillis by remember { mutableStateOf(initialMillis ?: System.currentTimeMillis()) }

    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }

    OutlinedTextField(
        value = dateFormat.format(Date(displayMillis)),
        onValueChange = {}, // désactivé : on ne tape pas manuellement
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = {
                val calendar = Calendar.getInstance().apply { timeInMillis = displayMillis }
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        TimePickerDialog(
                            context,
                            { _, hourOfDay, minute ->
                                calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                                displayMillis = calendar.timeInMillis
                                onDateTimeSelected(displayMillis)
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        ).show()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Icon(Icons.Default.Schedule, contentDescription = "Sélectionner date/heure")
            }
        },
        //modifier = modifier.fillMaxWidth()
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableForm(
    fields: List<FormField>,
    initialValues: Map<String, Any?> = emptyMap(),
    onSubmit: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null
) {
    val fieldValues = remember { mutableStateMapOf<String, String>() }

    // Format général
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    // Préremplir les valeurs initiales
    LaunchedEffect(Unit) {
        val now = System.currentTimeMillis()
        val tomorrow9AM = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
        }.timeInMillis

        fields.forEach { field ->
            val initial = initialValues?.get(field.name) ?: field.initialValue
            val defaultDate = when (field.name) {
                "applicationDate", "date", "dateTime" -> now.toString()
                "returnDate", "testsDeadline" -> tomorrow9AM.toString()
                else -> null
            }

            // Entretien spécial -> Demain 9h
            val entretienDefaultDateTime = if (field.name == "dateTime" && fields.any { it.label.contains("entretien", ignoreCase = true) }) {
                LocalDate.now().plusDays(1).atTime(9, 0).format(dateTimeFormatter)
            } else null

            fieldValues[field.name] = (initial ?: entretienDefaultDateTime ?: defaultDate ?: "").toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
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
                    ModernDateTimePickerField(
                        label = field.label,
                        initialMillis = fieldValues[field.name]?.toLongOrNull(),
                        onDateTimeSelected = { if (!field.readOnly) fieldValues[field.name] = it.toString() },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FieldType.DROPDOWN -> {
                    var showAddDialog by remember { mutableStateOf(false) }
                    var newOptionText by remember { mutableStateOf("") }

                    val options = remember { mutableStateListOf<String>().apply { addAll(field.options ?: emptyList()) } }
                    var expanded by remember { mutableStateOf(false) }

                    var optionBeingEdited by remember { mutableStateOf<String?>(null) }
                    var editDialogText by remember { mutableStateOf("") }

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
                                        text = {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(option, modifier = Modifier.weight(1f))

                                                IconButton(onClick = {
                                                    optionBeingEdited = option
                                                    editDialogText = option
                                                }) {
                                                    Icon(Icons.Default.Edit, contentDescription = "Modifier")
                                                }

                                                IconButton(onClick = {
                                                    options.remove(option)
                                                    field.onOptionRemoved?.invoke(option)
                                                }) {
                                                    Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                                                }
                                            }
                                        },
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
                        if (optionBeingEdited != null) {
                            AlertDialog(
                                onDismissRequest = { optionBeingEdited = null },
                                confirmButton = {
                                    TextButton(onClick = {
                                        val old = optionBeingEdited!!
                                        val new = editDialogText.trim()
                                        if (new.isNotBlank() && new != old) {
                                            val index = options.indexOf(old)
                                            if (index != -1) {
                                                options[index] = new
                                                field.onOptionRenamed?.invoke(old, new)
                                                if (fieldValues[field.name] == old) {
                                                    fieldValues[field.name] = new
                                                }
                                            }
                                        }
                                        optionBeingEdited = null
                                    }) {
                                        Text("Modifier")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = {
                                        optionBeingEdited = null
                                    }) {
                                        Text("Annuler")
                                    }
                                },
                                title = { Text("Modifier l'option") },
                                text = {
                                    OutlinedTextField(
                                        value = editDialogText,
                                        onValueChange = { editDialogText = it },
                                        label = { Text("Nouvelle valeur") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            )
                        }
                    }
                }
                FieldType.SELECTION -> {
                    var expanded by remember { mutableStateOf(false) }
                    val input = fieldValues[field.name] ?: ""
                    val suggestions = field.options?.filter { it.contains(input, ignoreCase = true) } ?: emptyList()

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = input,
                            onValueChange = {
                                if (!field.readOnly) fieldValues[field.name] = it
                                expanded = true
                            },
                            label = { Text(field.label) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            readOnly = false,
                            singleLine = true
                        )

                        ExposedDropdownMenu(
                            expanded = expanded && suggestions.isNotEmpty(),
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

                FieldType.SUGGESTION_TEXT -> {
                    var expanded by remember { mutableStateOf(false) }
                    val input = fieldValues[field.name] ?: ""
                    val suggestions = field.options?.filter { it.contains(input, ignoreCase = true) }?.take(5) ?: emptyList()

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = input,
                            onValueChange = {
                                if (!field.readOnly) fieldValues[field.name] = it
                                expanded = true
                            },
                            label = { Text(field.label) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            readOnly = false, // IMPORTANT : laisser editable
                            singleLine = true
                        )

                        ExposedDropdownMenu(
                            expanded = expanded && suggestions.isNotEmpty(),
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

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        ) {
            if (onCancel != null) {
                OutlinedButton(onClick = onCancel) {
                    Text("Annuler")
                }
                Spacer(Modifier.width(12.dp))
            }
            Button(
                onClick = { onSubmit(
                    fieldValues.mapValues { (key, value) ->
                        if (key.endsWith("Id") || key == "contactId") {
                            // Exemple de format : "Nom complet (ID)" -> on récupère juste l'ID entre parenthèse
                            value.substringAfterLast("(").removeSuffix(")").trim()
                        } else value
                    }
                ) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enregistrer")
            }
        }
    }
}
