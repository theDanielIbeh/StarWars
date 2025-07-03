package com.example.starwars.ui.presentation.viewmodels.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.local.datastore.model.CategoryModel
import com.example.starwars.data.local.datastore.repository.CategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for interacting with the [CategoryRepo] DataStore.
 * It manages saving and retrieving the selected category.
 *
 * @param categoryRepo Repository instance for accessing the category DataStore.
 */
@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepo: CategoryRepo) : ViewModel() {

    /**
     * A [StateFlow] that emits the current [CategoryModel] stored in the DataStore.
     * Uses [SharingStarted.WhileSubscribed] to conserve resources.
     */
    val getData : StateFlow<CategoryModel> =
        categoryRepo.getDataStore().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CategoryModel(null)
        )


    /**
     * Saves a new [CategoryModel] into the DataStore.
     *
     * @param categoryModel The category data to be saved.
     */
    fun saveData(categoryModel: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepo.saveDataStore(categoryModel)
        }
    }

    /**
     * Clears the DataStore.
     */
    fun clearCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepo.clearDataStore()
        }
    }

}