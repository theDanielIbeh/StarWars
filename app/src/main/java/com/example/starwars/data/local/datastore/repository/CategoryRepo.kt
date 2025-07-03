package com.example.starwars.data.local.datastore.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.starwars.data.local.datastore.model.CategoryModel
import com.example.starwars.data.local.datastore.repository.CategoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * The name used for the DataStore file.
 */
const val DataStore_NAME = "Category_DataStore"

/**
 * Extension property to create a singleton instance of [DataStore] for [Preferences].
 */
val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)

/**
 * Repository implementation for managing category persistence using [DataStore].
 *
 * @param context The application context required to access the DataStore.
 */
class CategoryRepo(private val context: Context) : CategoryInterface {

    companion object {
        /**
         * Key used to store/retrieve the category string from the DataStore.
         */
        val CATEGORY = stringPreferencesKey("CATEGORY")
    }

    /**
     * Saves the [categoryModel] to the DataStore.
     *
     * **Note:** This implementation assumes the category is non-null.
     * Ensure that null values are not passed to avoid a [NullPointerException].
     */
    override suspend fun saveDataStore(categoryModel: CategoryModel) {
        context.datastore.edit { data ->
            data[CATEGORY] = categoryModel.category!!

        }
    }

    /**
     * Reads the stored category from the DataStore and maps it into a [CategoryModel].
     * If no category is stored, it returns an empty string as the default.
     *
     * @return A [Flow] emitting the stored [CategoryModel].
     */
    override fun getDataStore(): Flow<CategoryModel> =
        context.datastore.data.map { data ->
            CategoryModel(
                category = data[CATEGORY]
            )
        }

    override suspend fun clearDataStore() {
        context.datastore.edit { data ->
            data.clear()
        }
    }
}

