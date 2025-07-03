package com.example.starwars.util

/**
 * Object that holds application-wide constant values such as API base URL
 * and logging tags for network-related exceptions.
 */
object Constants {

    /** Base URL of the Star Wars API */
    const val BASE_URL = "https://swapi.info/api/"

    /** Tag used for logging IOExceptions */
    const val IOException_TAG = "IOException"

    /** Tag used for logging HttpExceptions */
    const val HttpException_TAG = "HttpException"

    /** Tag used for logging general exceptions */
    const val Exception_TAG = "Exception"
}