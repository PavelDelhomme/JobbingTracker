package com.delhomme.jobbingtrack.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.ui.components.SwipeToDismissItem


@Composable
fun <T> ListScreen(
    dateProvider: (T) -> String?,
    titleProvider: (T) -> String,
    centerInfoProvider: (T) -> String,
    bottomLeftInfoProvider: (T) -> String?,
    items: List<T>,
    onItemClick: (T) -> Unit,
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