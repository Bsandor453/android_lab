package hu.bme.cryptochecker.modules.network

import hu.bme.cryptochecker.model.dto.Cryptocurrency
import hu.bme.cryptochecker.model.dto.CoinDescription
import hu.bme.cryptochecker.model.dto.HistoricalPrices
import retrofit2.Response
import retrofit2.http.*

interface CryptoApi {

    // Real API calls, that use actual resources

    /**
     * Get general information about given coins.
     * @param vs_currency The target currency of market data (usd, eur, jpy, etc.). (required)
     * @param ids The ids of the coin, comma separated cryptocurrency symbols (base).
     * When left empty, returns numbers the coins observing the params limit and start.
     * @param order Sort results by different metrics. Default value: market_cap_desc.
     * Available values : market_cap_desc, market_cap_asc, gecko_desc, gecko_asc, volume_asc, volume_desc, id_asc, id_desc
     * @param per_page Total results per page. Valid values: [1, 2, ..., 250]. Default value : 100.
     * @param page Page through results. Default value : 1.
     * @return Response<List<Cryptocurrency>>
     */
    @GET("coins/markets")
    suspend fun getCoinsMarkets(
        @Query("vs_currency") vs_currency: String,
        @Query("ids") ids: String? = null,
        @Query("order") order: String? = null,
        @Query("per_page") per_page: String? = null,
        @Query("page") page: String? = null
    ): Response<List<Cryptocurrency>>

    /**
     * Get historical prices of a given coin.
     * @param coinId The coin id. (eg. bitcoin). (required)
     * @param vs_currency The target currency of market data (usd, eur, jpy, etc.). (required)
     * @param daysAgo Data up to number of days ago (eg. 1, 14, 30, max). (required)
     * @return Response<HistoricalPrices>
     */
    @GET("coins/{id}/market_chart")
    suspend fun getCoinsMarketChart(
        @Path("id") coinId: String,
        @Query("vs_currency") vs_currency: String,
        @Query("days") daysAgo: Int
    ): Response<HistoricalPrices>

    /**
     * Get the description of a coin.
     * @param coinId The coin id. (eg. bitcoin). (required)
     * @param localization Include all localized languages in response (true/false). Default: true.
     * @return Response<CoinDescription>
     */
    @GET("coins/{id}")
    suspend fun getCoinsDescription(
        @Path("id") coinId: String,
        @Query("localization") localization: Boolean? = null
    ): Response<CoinDescription>

    // Mock API calls, that are only used in mocked calls

    /**
     * Add a new coin.
     * @param coin Cryptocurrency object that you want to add. (required)
     * @return Response<Void>
     */
    @POST("coins")
    suspend fun addCoin(@Body coin: Cryptocurrency): Response<Void>

    /**
     * Update a coin.
     * @param coinId The coin id (eg. bitcoin). (required)
     * @return Response<Void>
     */
    @PUT("coins/{id}")
    suspend fun updateCoin(@Path("id") coinId: String): Response<Void>

    /**
     * Delete a coin.
     * @param coinId The coin id (eg. bitcoin). (required)
     * @return Response<Void>
     */
    @DELETE("coins/{id}")
    suspend fun deleteCoin(@Path("id") coinId: String): Response<Void>

}