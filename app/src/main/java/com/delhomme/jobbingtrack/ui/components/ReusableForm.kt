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
                FieldType.DATE, FieldType.TIME, FieldType.DROPDOWN -> {
                    // TODO plus tard : date picker / dropdown
                    OutlinedTextField(
                        value = fieldValues[field.name] ?: "",
                        onValueChange = { fieldValues[field.name] = it },
                        label = { Text(field.label) },
                        modifier = Modifier.fillMaxWidth()
                    )
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
