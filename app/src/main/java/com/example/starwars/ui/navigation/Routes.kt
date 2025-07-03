package com.example.starwars.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Sealed interface defining the navigation routes in the application.
 * Each route corresponds to a screen destination.
 */
sealed interface Routes {
    /**
     * Route for the list of articles screen.
     */
    @Serializable
    data object Home : Routes

    /**
     * Route for the article detail screen.
     *
     * @param category The unique identifier of the article to display
     */
    @Serializable
    data class Result(val category: String?) : Routes
}
