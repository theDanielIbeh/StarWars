package com.example.starwars.di.network

import android.content.Context
import com.example.starwars.util.network.AndroidConnectivityObserver
import com.example.starwars.util.network.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides a singleton implementation of [ConnectivityObserver].
 */
@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    /**
     * Provides a singleton instance of [ConnectivityObserver] using [AndroidConnectivityObserver].
     *
     * @param context The application context required for connectivity monitoring.
     * @return An instance of [ConnectivityObserver].
     */
    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context,
    ): ConnectivityObserver {
        return AndroidConnectivityObserver(context)
    }
}
