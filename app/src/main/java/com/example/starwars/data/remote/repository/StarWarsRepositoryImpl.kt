package com.example.starwars.data.remote.repository

import com.example.starwars.data.remote.network.StarWarsApi
import com.example.starwars.domain.model.film.Film
import com.example.starwars.domain.model.starships.StarShips
import com.example.starwars.domain.model.vehicle.Vehicle
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation of [StarWarsRepository] that uses [StarWarsApi] to fetch data.
 *
 * @property api An instance of [StarWarsApi] used to make network calls.
 */
class StarWarsRepositoryImpl @Inject constructor(
    private val api: StarWarsApi
) : StarWarsRepository {

    override suspend fun getFilms(): Response<Film> {
        return api.getFilms()
    }

    override suspend fun getVehicles(): Response<Vehicle> {
        return api.getVehicles()
    }

    override suspend fun getStarShips(): Response<StarShips> {
        return api.getStarShips()
    }
}