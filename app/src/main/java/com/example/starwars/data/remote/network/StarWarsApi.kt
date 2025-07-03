package com.example.starwars.data.remote.network

import com.example.starwars.domain.model.film.Film
import com.example.starwars.domain.model.starships.StarShips
import com.example.starwars.domain.model.vehicle.Vehicle
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit interface for accessing Star Wars API endpoints.
 */
interface StarWarsApi {

    /**
     * Fetches a list of films from the Star Wars API.
     *
     * @return A [Response] containing [Film] data.
     */
    @GET("films")
    suspend fun getFilms(): Response<Film>

    /**
     * Fetches a list of vehicles from the Star Wars API.
     *
     * @return A [Response] containing [Vehicle] data.
     */
    @GET("vehicles")
    suspend fun getVehicles(): Response<Vehicle>

    /**
     * Fetches a list of starships from the Star Wars API.
     *
     * @return A [Response] containing [StarShips] data.
     */
    @GET("starships")
    suspend fun getStarShips(): Response<StarShips>
}
