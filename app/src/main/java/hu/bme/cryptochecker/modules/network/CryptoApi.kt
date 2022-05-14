package hu.bme.cryptochecker.modules.network

import hu.bme.cryptochecker.model.dto.Cryptocurrency
import hu.bme.cryptochecker.model.dto.HistoricalPrices
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins/markets")
    suspend fun getCoinsMarkets(@Query("vs_currency") vs_currency: String): Response<List<Cryptocurrency>>

    @GET("coins/{id}/market_chart")
    suspend fun getCoinsMarketChart(@Path("id") coinId: String, @Query("vs_currency") vs_currency: String, @Query("days") daysAgo: Int): Response<HistoricalPrices>

}