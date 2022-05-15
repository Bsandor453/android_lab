package hu.bme.cryptochecker.modules.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    protected open fun baseUrl() = NetworkConfig.API_BASE_URL.toHttpUrl()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // Create builder
        val httpClientBuilder = OkHttpClient.Builder()

        // Add logging interceptor
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        httpClientBuilder.addInterceptor(logging)

        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("CryptoApi")
    fun provideCryptoApi(client: OkHttpClient): CryptoApi {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CryptoApi::class.java)
    }

}