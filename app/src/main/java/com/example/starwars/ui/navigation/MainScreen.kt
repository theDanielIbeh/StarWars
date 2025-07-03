package com.example.starwars.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.starwars.ui.presentation.screens.home.components.HomeScreen
import com.example.starwars.ui.presentation.screens.results.components.ResultsScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Results)
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    // Store the last searched category to pass to Results
    var lastSearchedCategory by remember { mutableStateOf<String?>(null) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == item,
                        onClick = { selectedItem = item }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            when (selectedItem) {
                is BottomNavItem.Home -> {
                    HomeScreen(
                        onNavigateToResults = {
                            lastSearchedCategory = it
                            selectedItem = BottomNavItem.Results
                        }
                    )
                }

                is BottomNavItem.Results -> {
                    ResultsScreen(
                        searchString = lastSearchedCategory,
                        onClear = { lastSearchedCategory = null }
                    )
                }
            }
        }
    }
}

/**
 * Represents a sealed class hierarchy for bottom navigation destinations.
 *
 * @property route The navigation [Routes] object for the screen.
 * @property title The label shown under the icon.
 * @property icon The icon shown in the bottom navigation item.
 */
sealed class BottomNavItem(
    val route: Routes,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Routes.Home,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Results : BottomNavItem(
        route = Routes.Result(null),
        title = "Results",
        icon = Icons.Default.Search
    )
}
