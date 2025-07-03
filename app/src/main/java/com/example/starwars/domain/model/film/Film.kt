package com.example.starwars.domain.model.film

/**
 * Represents a list of Star Wars films returned by the API.
 *
 * This class extends [ArrayList] to directly deserialize a JSON array of [FilmItem].
 */
class Film : ArrayList<FilmItem>()
