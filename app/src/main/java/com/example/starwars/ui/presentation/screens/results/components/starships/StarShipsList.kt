package com.example.starwars.ui.presentation.screens.results.components.starships

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwars.domain.model.starships.StarShipsItem
import com.example.starwars.ui.presentation.common.EmptyContent

@Composable
fun StarShipsList(
    starShips: List<StarShipsItem>,
    modifier: Modifier = Modifier,
    sortAscending: Boolean
) {
    if (starShips.isEmpty()) {
        EmptyContent()
    } else {
        val listState = rememberLazyListState()

        // Scroll to top when sort order changes
        LaunchedEffect(sortAscending) {
            listState.animateScrollToItem(0)
        }

        LazyColumn(
            state = listState,
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = starShips,
                key = { it.name ?: it.url ?: it.hashCode().toString() },
            ) { starship ->
                StarshipCard(starship = starship)
            }
        }
    }
}
