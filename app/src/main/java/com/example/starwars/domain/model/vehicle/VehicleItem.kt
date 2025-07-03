package com.example.starwars.domain.model.vehicle

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

/**
 * Data class representing an individual vehicle in the Star Wars universe.
 *
 * This model is used to parse the vehicle object from the API response.
 *
 * @property cargoCapacity The cargo capacity of the vehicle.
 * @property consumables The duration the vehicle can provide consumables for its crew.
 * @property costInCredits The cost of the vehicle in Galactic Credits.
 * @property created The ISO timestamp when the vehicle was created in the database.
 * @property crew The number of personnel required to operate the vehicle.
 * @property edited The ISO timestamp when the vehicle record was last edited.
 * @property films A list of film URLs this vehicle appeared in.
 * @property length The length of the vehicle.
 * @property manufacturer The name(s) of the manufacturer(s).
 * @property maxAtmospheringSpeed The maximum speed of the vehicle in atmosphere.
 * @property model The model name of the vehicle.
 * @property name The name of the vehicle.
 * @property passengers The number of passengers the vehicle can carry.
 * @property pilots A list of character URLs who have piloted the vehicle.
 * @property url The API URL for this vehicle resource.
 * @property vehicleClass The classification of the vehicle (e.g., wheeled, repulsorlift).
 */
@Serializable
data class VehicleItem(
    @SerializedName("cargo_capacity")
    val cargoCapacity: String? = null,
    val consumables: String? = null,
    @SerializedName("cost_in_credits")
    val costInCredits: String? = null,
    val created: String? = null,
    val crew: String? = null,
    val edited: String? = null,
    val films: List<String>? = null,
    val length: String? = null,
    val manufacturer: String? = null,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String? = null,
    val model: String? = null,
    val name: String? = null,
    val passengers: String? = null,
    val pilots: List<String>? = null,
    val url: String? = null,
    @SerializedName("vehicle_class")
    val vehicleClass: String? = null,
)
