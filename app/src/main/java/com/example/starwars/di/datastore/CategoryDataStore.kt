package com.example.starwars.di.datastore

import android.content.Context
import com.example.starwars.data.local.datastore.repository.CategoryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides a singleton instance of [CategoryRepo]
 * to be used as a data store repository throughout the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object CategoryDataStore {

    /**
     * Provides a singleton instance of [CategoryRepo].
     *
     * @param context The application context used to access the DataStore.
     * @return An instance of [CategoryRepo].
     */
    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): CategoryRepo = CategoryRepo(context)
}
