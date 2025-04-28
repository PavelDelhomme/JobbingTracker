package com.delhomme.jobbingtrack.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetManager(
    sheetContent: @Composable (dismissSheet: () -> Unit) -> Unit,
    triggerButton: @Composable (openSheet: () -> Unit) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    var isSheetOpen by remember { mutableStateOf(false) }

    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            sheetState = bottomSheetState
        ) {
            sheetContent { isSheetOpen = false }
        }
    }

    triggerButton { isSheetOpen = true }
}
