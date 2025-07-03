package com.example.starwars.ui.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.starwars.data.remote.repository.StarWarsRepository
import com.example.starwars.util.network.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the Home screen, managing user input and connectivity state.
 *
 * @property myRepo The repository used to fetch data from the API (injected via Hilt).
 * @property connectivityObserver Monitors internet connection status (injected via Hilt).
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myRepo: StarWarsRepository,
    private val connectivityObserver: ConnectivityObserver,
) : ViewModel() {
    /**
     * The current user input for the category search.
     */
    var inputText by mutableStateOf("")
        private set

    /**
     * Updates the [inputText] state with the new value.
     *
     * @param newValue The new text input from the user.
     */
    fun onTextChange(newValue: String) {
        inputText = newValue
    }

    /**
     * Resets the [inputText] to an empty string.
     */
    fun resetText() {
        inputText = ""
    }
}
