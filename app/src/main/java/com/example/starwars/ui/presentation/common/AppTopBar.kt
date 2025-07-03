package com.example.starwars.ui.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

/**
 * A customizable top app bar that optionally shows a back icon and sort icon.
 *
 * @param onSortClick Called when the sort icon is clicked.
 * @param onClear Called when the clear icon is clicked.
 * @param title The title to display in the center of the app bar.
 * @param showSortIcon Whether to show the sort button (default: true).
 * @param showClearIcon Whether to show the clear button (default: false).
 * @param sortAscending Indicates the current sort direction.
 *                      If true, shows upward arrow (A–Z), otherwise downward (Z–A).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onSortClick: () -> Unit,
    onClear: () -> Unit,
    title: String,
    showSortIcon: Boolean = false,
    showClearIcon: Boolean = false,
    sortAscending: Boolean = true,
) {
        TopAppBar(
            title = { Text(text = title) },
            actions = {
                if (showSortIcon) {
                    IconButton(onClick = onSortClick) {
                        Row {
                            Icon(
                                imageVector = if (sortAscending) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = if (sortAscending) "Sort A-Z" else "Sort Z-A"
                            )
                        }
                    }
                }
                if (showClearIcon) {
                    IconButton(onClick = onClear) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Clear"
                        )
                    }
                }
            }
        )
}
