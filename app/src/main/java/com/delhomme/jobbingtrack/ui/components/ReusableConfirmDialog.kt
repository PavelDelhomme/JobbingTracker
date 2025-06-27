package com.delhomme.jobbingtrack.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ReusableConfirmDialog(
    show: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmButtonText: String = "Confirmer",
    dismissButtonText: String = "Annuler"
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(message)},
            confirmButton = {
                TextButton(onClick = onConfirm) { Text(confirmButtonText) }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) { Text(dismissButtonText) }
            }
        )
    }
}