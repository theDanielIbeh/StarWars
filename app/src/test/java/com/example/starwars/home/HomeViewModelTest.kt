package com.example.starwars.home

import com.example.starwars.data.remote.repository.StarWarsRepository
import com.example.starwars.ui.presentation.screens.home.HomeViewModel
import com.example.starwars.util.network.ConnectivityObserver
import org.junit.Before
import org.mockito.Mockito.mock
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val mockRepo: StarWarsRepository = mock()
    private val mockConnectivityObserver: ConnectivityObserver = mock()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(mockRepo, mockConnectivityObserver)
    }

    @Test
    fun `onTextChange updates inputText`() {
        viewModel.onTextChange("Hello")
        assertEquals("Hello", viewModel.inputText)
    }

    @Test
    fun `resetText sets inputText to empty string`() {
        viewModel.onTextChange("Star Wars")
        viewModel.resetText()
        assertEquals("", viewModel.inputText)
    }
}
