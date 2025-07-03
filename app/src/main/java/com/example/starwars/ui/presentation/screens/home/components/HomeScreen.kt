package com.example.starwars.ui.presentation.screens.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwars.ui.presentation.common.AppTopBar
import com.example.starwars.data.local.datastore.model.CategoryModel
import com.example.starwars.ui.presentation.screens.home.HomeViewModel
import com.example.starwars.ui.presentation.viewmodels.category.CategoryViewModel
import kotlinx.coroutines.launch
import com.example.starwars.R.string as AppText

/**
 * Composable that displays the Home screen UI where users can enter a category
 * to navigate to the Results screen.
 *
 * @param onNavigateToResults Callback to navigate to the Results screen with the entered category.
 * @param modifier Modifier applied to the screen layout.
 * @param homeViewModel The [HomeViewModel] instance (injected via Hilt).
 * @param categoryViewModel The [CategoryViewModel] for saving the selected category (injected via Hilt).
 */
@Composable
fun HomeScreen(
    onNavigateToResults: (String) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
) {
    val topBarTitle = AppText.home
    val validInputs = listOf("starships", "films", "vehicles")

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val text = homeViewModel.inputText

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(topBarTitle),
                onSortClick = {},
                onClear = {},
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = modifier.imePadding() // This handles keyboard padding
    ) { padding ->
        HomeContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()), // Add scrolling capability
            text = text,
            onTextChange = homeViewModel::onTextChange,
            onSubmitClick = {
                val input = text.trim().lowercase()
                keyboardController?.hide() // Hide keyboard

                if (input in validInputs) {
                    onNavigateToResults(input)

                    categoryViewModel.saveData(
                        CategoryModel(category = input)
                    )

                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            "Invalid input. Try 'starships', 'films' or 'vehicles'",
                            duration = SnackbarDuration.Long,
                        )
                    }
                }
            },
        )
    }
}

/**
 * Preview composable for the [HomeScreen] for visual inspection in Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToResults = {},
    )
}