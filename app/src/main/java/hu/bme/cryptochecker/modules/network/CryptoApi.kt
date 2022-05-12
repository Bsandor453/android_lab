package hu.bme.cryptochecker.modules.network

import hu.bme.cryptochecker.model.Cryptocurrency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins/markets")
    suspend fun getCoinsMarkets(@Query("vs_currency") vs_currency: String): Response<Array<Cryptocurrency>>

}