package com.example.starwars.ui.presentation.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.remote.repository.StarWarsRepository
import com.example.starwars.domain.model.film.Film
import com.example.starwars.domain.model.film.FilmItem
import com.example.starwars.domain.model.starships.StarShips
import com.example.starwars.domain.model.starships.StarShipsItem
import com.example.starwars.domain.model.vehicle.Vehicle
import com.example.starwars.domain.model.vehicle.VehicleItem
import com.example.starwars.util.network.ConnectivityObserver
import com.example.starwars.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for managing and exposing data for the Results screen.
 * It handles data fetching from the repository, network connectivity monitoring,
 * retry logic, and sorting logic for Starships, Films, and Vehicles.
 *
 * @param myRepo Repository used to fetch data from the API.
 * @param connectivityObserver Observes network connectivity state.
 */
@HiltViewModel
class ResultsViewModel
@Inject
constructor(
    private val myRepo: StarWarsRepository,
    private val connectivityObserver: ConnectivityObserver,
) : ViewModel() {

    /**
     * Observes the network connectivity status as a [StateFlow].
     * Emits `true` if connected, `false` otherwise.
     */
    val isConnected =
        connectivityObserver
            .isConnected
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                false,
            )

    private val retryMyVehicleItemTrigger = MutableStateFlow(0)
    private val retryMyStarShipsTrigger = MutableStateFlow(0)
    private val retryMyFilmTrigger = MutableStateFlow(0)

    /**
     * Exposes the state of vehicle data as a [StateFlow] of [NetworkResult].
     * Automatically retries when [retryMyVehicleItemTrigger] is incremented.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val getVehiclesState: StateFlow<NetworkResult<Vehicle>> =
        retryMyVehicleItemTrigger
            .flatMapLatest {
                flow {
                    emit(NetworkResult.Loading()) // Emit loading state
                    try {
                        val response = myRepo.getVehicles()
                        if (response.isSuccessful) {
                            emit(NetworkResult.Success(response.body()!!)) // Success case
                        } else {
                            emit(NetworkResult.Error(response.message())) // Error case from response
                        }
                    } catch (e: IOException) {
                        emit(NetworkResult.Error("Network Error: ${e.localizedMessage}")) // Network exception
                    } catch (e: HttpException) {
                        emit(NetworkResult.Error("HTTP Error: ${e.localizedMessage}")) // HTTP error
                    } catch (e: Exception) {
                        emit(NetworkResult.Error("Unknown Error: ${e.localizedMessage}")) // Generic error
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = NetworkResult.Loading(),
            )

    /**
     * Exposes the state of starship data as a [StateFlow] of [NetworkResult].
     * Automatically retries when [retryMyStarShipsTrigger] is incremented.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val getStarShipState: StateFlow<NetworkResult<StarShips>> =
        retryMyStarShipsTrigger
            .flatMapLatest {
                flow {
                    emit(NetworkResult.Loading()) // Emit loading state
                    try {
                        val response = myRepo.getStarShips()
                        if (response.isSuccessful) {
                            emit(NetworkResult.Success(response.body()!!)) // Success case
                        } else {
                            emit(NetworkResult.Error(response.message())) // Error case from response
                        }
                    } catch (e: IOException) {
                        emit(NetworkResult.Error("Network Error: ${e.localizedMessage}")) // Network exception
                    } catch (e: HttpException) {
                        emit(NetworkResult.Error("HTTP Error: ${e.localizedMessage}")) // HTTP error
                    } catch (e: Exception) {
                        emit(NetworkResult.Error("Unknown Error: ${e.localizedMessage}")) // Generic error
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = NetworkResult.Loading(),
            )

    /**
     * Exposes the state of film data as a [StateFlow] of [NetworkResult].
     * Automatically retries when [retryMyFilmTrigger] is incremented.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val getFilmState: StateFlow<NetworkResult<Film>> =
        retryMyFilmTrigger
            .flatMapLatest {
                flow {
                    emit(NetworkResult.Loading()) // Emit loading state
                    try {
                        val response = myRepo.getFilms()
                        if (response.isSuccessful) {
                            emit(NetworkResult.Success(response.body()!!)) // Success case
                        } else {
                            emit(NetworkResult.Error(response.message())) // Error case from response
                        }
                    } catch (e: IOException) {
                        emit(NetworkResult.Error("Network Error: ${e.localizedMessage}")) // Network exception
                    } catch (e: HttpException) {
                        emit(NetworkResult.Error("HTTP Error: ${e.localizedMessage}")) // HTTP error
                    } catch (e: Exception) {
                        emit(NetworkResult.Error("Unknown Error: ${e.localizedMessage}")) // Generic error
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = NetworkResult.Loading(),
            )

    // Retry function that triggers the retry flow
    fun retryMyVehicleItemTrigger() {
        retryMyVehicleItemTrigger.value += 1
    }

    // Retry function that triggers the retry flow
    fun retryMyStarShipsTrigger() {
        retryMyStarShipsTrigger.value += 1
    }

    // Retry function that triggers the retry flow
    fun retryMyFilmTrigger() {
        retryMyFilmTrigger.value += 1
    }

    // Add sorting state
    private val _sortAscending = MutableStateFlow(true)
    val sortAscending: StateFlow<Boolean> = _sortAscending.asStateFlow()


    // Toggle sort order
    fun toggleSortOrder() {
        _sortAscending.value = !_sortAscending.value
    }

    // Function to sort starships by name
    fun sortStarshipsByName(
        starships: List<StarShipsItem>,
        ascending: Boolean
    ): List<StarShipsItem> {
        return if (ascending) {
            starships.sortedBy { it.name?.lowercase() ?: "" }
        } else {
            starships.sortedByDescending { it.name?.lowercase() ?: "" }
        }
    }

    // Function to sort FilmItem by title
    fun sortFilmsByTitle(
        films: List<FilmItem>,
        ascending: Boolean
    ): List<FilmItem> {
        return if (ascending) {
            films.sortedBy { it.title?.lowercase() ?: "" }
        } else {
            films.sortedByDescending { it.title?.lowercase() ?: "" }
        }
    }

    // Function to sort VehicleItem by name
    fun sortVehiclesByTitle(
        vehicles: List<VehicleItem>,
        ascending: Boolean
    ): List<VehicleItem> {
        return if (ascending) {
            vehicles.sortedBy { it.name?.lowercase() ?: "" }
        } else {
            vehicles.sortedByDescending { it.name?.lowercase() ?: "" }
        }
    }
}
