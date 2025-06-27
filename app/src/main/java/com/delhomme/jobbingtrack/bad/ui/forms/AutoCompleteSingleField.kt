package com.delhomme.jobbingtrack.bad.ui.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlin.math.exp

@Composable
fun AutoCompleteSingleField(
    label: String,
    allOptions: List<String>,
    selected: String,
    onSelected: (String) -> Unit,
    onNewOption: (String) -> Unit,
) {
    var text by remember { mutableStateOf(selected) }
    var expanded by remember { mutableStateOf(false) }
    var filtered = allOptions.filter { it.contains(text, ignoreCase = true) && it != text }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                expanded = it.isNotBlank()
            },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        DropdownMenu(
            expanded = expanded && filtered.isNotEmpty(),
            onDismissRequest = { expanded = false },
        ) {
            filtered.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        text = option
                        expanded = false
                    }
                )
            }
            if (text.isNotBlank() && filtered.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("Add \"$text\"") },
                    onClick = {
                        onNewOption(text)
                        onSelected(text)
                        expanded = false
                    }
                )
            }
        }
    }
}