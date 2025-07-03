package com.example.starwars.domain.model.vehicle

/**
 * A list of [VehicleItem] objects representing a collection of Star Wars vehicles.
 *
 * This class extends [ArrayList] and is typically used to deserialize the JSON array
 * response from the Star Wars API endpoint `/vehicles`.
 */
class Vehicle : ArrayList<VehicleItem>()
