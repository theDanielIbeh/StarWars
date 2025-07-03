package com.example.starwars.ui.presentation.screens.results.components.starships

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.starwars.ui.presentation.common.ErrorScreen
import com.example.starwars.ui.presentation.common.LoadingScreen
import com.example.starwars.ui.presentation.screens.results.ResultsViewModel
import com.example.starwars.util.network.NetworkResult

@Composable
fun StarShipsContent(
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel,
    context: Context,
) {
    val getStarShipState by viewModel.getStarShipState.collectAsStateWithLifecycle()
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()
    val sortAscending by viewModel.sortAscending.collectAsStateWithLifecycle()


    when (getStarShipState) {
        is NetworkResult.Loading -> {
            LoadingScreen(modifier = modifier)
        }

        is NetworkResult.Error -> {
            ErrorScreen(
                modifier = modifier,
                errorMessage = (getStarShipState as NetworkResult.Error).message.toString(),
                onRetry = {
                    if (isConnected) {
                        viewModel.retryMyStarShipsTrigger()
                    } else {
                        Toast.makeText(context, "No internet available", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            )
        }

        is NetworkResult.Success -> {
            getStarShipState.data?.let {
                // Sort the starships before displaying
                val sortedStarships = viewModel.sortStarshipsByName(it.toList(), sortAscending)

                StarShipsList(
                    starShips = sortedStarships,
                    modifier = modifier,
                    sortAscending = sortAscending
                )
            }
        }
    }
}