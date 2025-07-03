package com.example.starwars.domain.model.starships

/**
 * A list of [StarShipsItem] representing multiple starships retrieved from the API.
 *
 * This class extends [ArrayList] to enable direct deserialization from a JSON array
 * into a list of starship objects using Retrofit or kotlinx.serialization.
 */
class StarShips : ArrayList<StarShipsItem>()
