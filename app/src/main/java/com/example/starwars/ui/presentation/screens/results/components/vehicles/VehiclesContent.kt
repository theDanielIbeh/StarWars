package com.example.starwars.ui.presentation.screens.results.components.vehicles

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
fun VehiclesContent(
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel,
    context: Context,
) {
    val getVehiclesState by viewModel.getVehiclesState.collectAsStateWithLifecycle()
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()
    val sortAscending by viewModel.sortAscending.collectAsStateWithLifecycle()

    when (getVehiclesState) {
        is NetworkResult.Loading -> {
            LoadingScreen(modifier =modifier)
        }

        is NetworkResult.Error -> {
            ErrorScreen(
                modifier = modifier,
                errorMessage = (getVehiclesState as NetworkResult.Error).message.toString(),
                onRetry = {
                    if (isConnected) {
                        viewModel.retryMyVehicleItemTrigger()
                    } else {
                        Toast.makeText(context, "No internet available", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            )
        }

        is NetworkResult.Success -> {
            getVehiclesState.data?.let {
                // Sort the vehicles before displaying
                val sortedVehicles = viewModel.sortVehiclesByTitle(it.toList(), sortAscending)

                MyVehiclesList(
                    vehicles = sortedVehicles,
                    modifier = modifier,
                    sortAscending = sortAscending
                )
            }
        }
    }
}