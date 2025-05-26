package com.delhomme.jobbingtrack.ui.components.lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.classes.EntretienStyle
import com.delhomme.jobbingtrack.data.classes.EntretienType
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.ui.components.items.SwipeToDismissItem

@Composable
fun <T> ListScreen(
    dateProvider: (T) -> String?,
    titleProvider: (Entretien) -> EntretienType,
    centerInfoProvider: (Entretien) -> EntretienStyle,
    bottomLeftInfoProvider: (T) -> String?,
    items: List<T>,
    onItemClick: (EntretienWithContacts) -> Unit,
    onEdit: ((T) -> Unit)? = null,
    onArchive: ((T) -> Unit)? = null,
    onDelete: ((T) -> Unit)? = null,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            SwipeToDismissItem(
                item = item,
                onEdit = { onEdit?.invoke(item) },
                onArchive = { onArchive?.invoke(item) },
                onDelete = { onDelete?.invoke(item) },
            ) {
                CustomListItemCard(
                    date = dateProvider(item),
                    title = titleProvider(item).toString(),
                    centerInfo = centerInfoProvider(item).toString(),
                    bottomLeftInfo = bottomLeftInfoProvider(item),
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}