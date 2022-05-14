package hu.bme.cryptochecker.ui.details

import hu.bme.cryptochecker.model.dto.HistoricalPrices
import hu.bme.cryptochecker.modules.network.CryptoApi
import javax.inject.Inject
import javax.inject.Named

class DetailsRepository @Inject constructor(@Named("CryptoApi") private val cryptoApi: CryptoApi) {

    suspend fun getHistoricalPrice(coinId: String, daysAgo: Int): HistoricalPrices {
        return cryptoApi.getCoinsMarketChart(coinId,"usd", daysAgo).body()!!
    }

}