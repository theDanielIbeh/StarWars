package com.example.starwars.data.remote.repository

import com.example.starwars.domain.model.film.Film
import com.example.starwars.domain.model.starships.StarShips
import com.example.starwars.domain.model.vehicle.Vehicle
import retrofit2.Response

/**
 * Repository interface for fetching Star Wars-related data.
 */
interface StarWarsRepository {

    /**
     * Retrieves a list of films.
     *
     * @return A [Response] containing [Film] data.
     */
    suspend fun getFilms(): Response<Film>

    /**
     * Retrieves a list of vehicles.
     *
     * @return A [Response] containing [Vehicle] data.
     */
    suspend fun getVehicles(): Response<Vehicle>

    /**
     * Retrieves a list of starships.
     *
     * @return A [Response] containing [StarShips] data.
     */
    suspend fun getStarShips(): Response<StarShips>
}