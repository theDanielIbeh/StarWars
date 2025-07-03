package com.example.starwars.results

import app.cash.turbine.test
import com.example.starwars.data.remote.repository.StarWarsRepository
import com.example.starwars.domain.model.starships.StarShipsItem
import com.example.starwars.domain.model.vehicle.Vehicle
import com.example.starwars.domain.model.vehicle.VehicleItem
import com.example.starwars.ui.presentation.screens.results.ResultsViewModel
import com.example.starwars.util.network.ConnectivityObserver
import com.example.starwars.util.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail

@ExperimentalCoroutinesApi
class ResultsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockRepo: StarWarsRepository = mock()
    private val mockConnectivityObserver: ConnectivityObserver = mock()

    private lateinit var viewModel: ResultsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        whenever(mockConnectivityObserver.isConnected).thenReturn(MutableStateFlow(true))
        viewModel = ResultsViewModel(mockRepo, mockConnectivityObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getVehiclesState emits Success when repo returns success`() = runTest {
        // Arrange
        val vehicle = Vehicle()
        vehicle.add(VehicleItem(name = "Speeder"))

        val mockResponse = Response.success(vehicle)
        whenever(mockRepo.getVehicles()).thenReturn(mockResponse)

        // Act + Assert
        viewModel.getVehiclesState.test {
            // Collect the initial Loading (from the ViewModel's initialValue)
            assert(awaitItem() is NetworkResult.Loading)

            // Now trigger the fetch
            viewModel.retryMyVehicleItemTrigger()

            // Expect a second Loading
            assert(awaitItem() is NetworkResult.Loading)

            // Now wait for the Success response
            val result = awaitItem()

            if (result is NetworkResult.Success) {
                assertEquals("Speeder", result.data?.firstOrNull()?.name)
            } else {
                fail("Expected NetworkResult.Success, but got $result")
            }

            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `sortStarshipsByName sorts ascending and descending`() {
        val unsortedList = listOf(
            StarShipsItem(name = "X-Wing"),
            StarShipsItem(name = "A-Wing"),
            StarShipsItem(name = "Y-Wing"),
        )

        val ascending = viewModel.sortStarshipsByName(unsortedList, true)
        assertEquals(listOf("A-Wing", "X-Wing", "Y-Wing"), ascending.map { it.name })

        val descending = viewModel.sortStarshipsByName(unsortedList, false)
        assertEquals(listOf("Y-Wing", "X-Wing", "A-Wing"), descending.map { it.name })
    }

    @Test
    fun `toggleSortOrder switches between true and false`() = runTest {
        assertTrue(viewModel.sortAscending.value)
        viewModel.toggleSortOrder()
        assertFalse(viewModel.sortAscending.value)
    }
}
