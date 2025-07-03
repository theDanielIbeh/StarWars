package com.example.starwars.ui.presentation.screens.results.components.films

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
fun FilmsContent(
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel,
    context: Context,
) {
    val getFilmState by viewModel.getFilmState.collectAsStateWithLifecycle()
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()
    val sortAscending by viewModel.sortAscending.collectAsStateWithLifecycle()

    when (getFilmState) {
        is NetworkResult.Loading -> {
            LoadingScreen(modifier = modifier)
        }

        is NetworkResult.Error -> {
            ErrorScreen(
                modifier = modifier,
                errorMessage = (getFilmState as NetworkResult.Error).message.toString(),
                onRetry = {
                    if (isConnected) {
                        viewModel.retryMyFilmTrigger()
                    } else {
                        Toast.makeText(context, "No internet available", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            )
        }

        is NetworkResult.Success -> {
            getFilmState.data?.let {
                // Sort the films before displaying
                val sortedFilms= viewModel.sortFilmsByTitle(it.toList(), sortAscending)

                MyFilmsList(
                    films = sortedFilms,
                    modifier = modifier,
                    sortAscending = sortAscending
                )
            }
        }
    }
}
