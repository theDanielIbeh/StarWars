package com.example.starwars.data.local.datastore.repository

import com.example.starwars.data.local.datastore.model.CategoryModel
import kotlinx.coroutines.flow.Flow

/**
* Interface defining the contract for saving and retrieving category data
* from a DataStore.
*/
interface CategoryInterface {

    /**
     * Saves the given [CategoryModel] into the DataStore.
     *
     * @param categoryModel The category data to be saved.
     */
    suspend fun saveDataStore(categoryModel: CategoryModel)

    /**
     * Retrieves the category data as a [Flow] of [CategoryModel].
     *
     * @return A Flow that emits the stored category.
     */
    fun getDataStore(): Flow<CategoryModel>

    /**
     * Clears the DataStore.
     */
    suspend fun clearDataStore()

}