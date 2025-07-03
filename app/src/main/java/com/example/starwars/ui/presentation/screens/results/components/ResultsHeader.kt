package com.example.starwars.ui.presentation.screens.results.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultsHeader(
    categoryName: String,
    sortAscending: Boolean,
    onSortToggle: () -> Unit,
    onClearResults: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Results for \"$categoryName\"",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
        )

        Row {
            Button(onClick = onSortToggle) {
                Text(if (sortAscending) "Sort ↓" else "Sort ↑")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onClearResults) {
                Text("Clear")
            }
        }
    }
}
