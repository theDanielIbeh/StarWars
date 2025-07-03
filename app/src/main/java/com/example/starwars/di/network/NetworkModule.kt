package com.example.starwars.di.network

import com.example.starwars.data.remote.network.StarWarsApi
import com.example.starwars.data.remote.repository.StarWarsRepository
import com.example.starwars.data.remote.repository.StarWarsRepositoryImpl
import com.example.starwars.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Hilt module that provides network-related dependencies such as
 * [OkHttpClient], [Retrofit], and API/repository implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * A reusable [HttpLoggingInterceptor] configured to log the full request and response body.
     */
    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Provides a singleton instance of [OkHttpClient] configured with logging and timeouts.
     *
     * @return Configured [OkHttpClient] instance.
     */
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides a singleton instance of [Retrofit] configured with the base URL,
     * [OkHttpClient], and a Gson converter.
     *
     * @param okHttpClient The [OkHttpClient] to be used for network calls.
     * @return Configured [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides a singleton instance of the [StarWarsApi] Retrofit interface.
     *
     * @param retrofit The [Retrofit] instance used to create the API implementation.
     * @return An implementation of [StarWarsApi].
     */
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): StarWarsApi {
        return retrofit.create(StarWarsApi::class.java)
    }

    /**
     * Provides a singleton implementation of [StarWarsRepository] by injecting [StarWarsRepositoryImpl].
     *
     * @param myRepositoryImpl The implementation of the [StarWarsRepository] interface.
     * @return The [StarWarsRepository] instance to be used in the app.
     */
    @Provides
    @Singleton
    fun provideMyRepo(myRepositoryImpl: StarWarsRepositoryImpl): StarWarsRepository {
        return myRepositoryImpl
    }
}
