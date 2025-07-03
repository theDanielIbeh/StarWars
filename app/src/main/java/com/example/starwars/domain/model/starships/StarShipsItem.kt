package com.example.starwars.domain.model.starships

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Data class representing a single starship in the Star Wars universe.
 *
 * This class maps the fields from the Star Wars API starships endpoint.
 *
 * @property cargoCapacity Maximum cargo capacity in kilograms (if known).
 * @property consumables How long the starship can provide consumables for its crew without resupply.
 * @property costInCredits Cost of the starship in Galactic Credits.
 * @property created Date the starship resource was created.
 * @property crew Number of crew members required to operate the starship.
 * @property edited Date the starship resource was last edited.
 * @property films List of film URLs this starship appeared in.
 * @property hyperdriveRating The hyperdrive rating of the starship (lower is faster).
 * @property length Length of the starship in meters.
 * @property mGLT Megalight per hour—distance the starship can travel in realspace in one hour.
 * @property manufacturer Name(s) of the manufacturer(s).
 * @property maxAtmospheringSpeed Maximum speed in atmosphere (if applicable).
 * @property model Starship's model designation.
 * @property name Starship's name.
 * @property passengers Number of passengers the starship can carry.
 * @property pilots List of character URLs for pilots of the starship.
 * @property starshipClass Type/class of the starship (e.g., "Starfighter", "Capital ship").
 * @property url Canonical URL to the starship resource.
 */
@Serializable
data class StarShipsItem(
    @SerializedName("cargo_capacity")
    val cargoCapacity: String? = null,
    val consumables: String? = null,
    @SerializedName("cost_in_credits")
    val costInCredits: String? = null,
    val created: String? = null,
    val crew: String? = null,
    val edited: String? = null,
    val films: List<String>? = null,
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String? = null,
    val length: String? = null,
    @SerializedName("MGLT")
    val mGLT: String? = null,
    val manufacturer: String? = null,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String? = null,
    val model: String? = null,
    val name: String? = null,
    val passengers: String? = null,
    val pilots: List<String>? = null,
    @SerializedName("starship_class")
    val starshipClass: String? = null,
    val url: String? = null,
)
