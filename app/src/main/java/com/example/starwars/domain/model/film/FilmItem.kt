package com.example.starwars.domain.model.film

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

/**
 * Represents a single Star Wars film item with detailed metadata.
 *
 * @property characters List of character URLs that appear in the film.
 * @property created Timestamp of when the film entry was created.
 * @property director Name of the director of the film.
 * @property edited Timestamp of the last edit to the film entry.
 * @property episodeId Episode number (e.g., 4 for "A New Hope").
 * @property openingCrawl The opening crawl text shown at the beginning of the film.
 * @property planets List of planet URLs featured in the film.
 * @property producer Name(s) of the producer(s).
 * @property releaseDate Official release date of the film in `YYYY-MM-DD` format.
 * @property species List of species URLs that appear in the film.
 * @property starships List of starship URLs featured in the film.
 * @property title Title of the film (e.g., "The Empire Strikes Back").
 * @property url API resource URL for this film.
 * @property vehicles List of vehicle URLs that appear in the film.
 */
@Serializable
data class FilmItem(
    val characters: List<String>? = null,
    val created: String? = null,
    val director: String? = null,
    val edited: String? = null,
    @SerializedName("episode_id")
    val episodeId: Int? = null,
    @SerializedName("opening_crawl")
    val openingCrawl: String? = null,
    val planets: List<String>? = null,
    val producer: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val species: List<String>? = null,
    val starships: List<String>? = null,
    val title: String? = null,
    val url: String? = null,
    val vehicles: List<String>? = null,
)
