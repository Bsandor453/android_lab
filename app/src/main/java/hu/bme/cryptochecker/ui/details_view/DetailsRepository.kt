package hu.bme.cryptochecker.ui.details_view

import hu.bme.cryptochecker.model.dto.HistoricalPricesDto
import hu.bme.cryptochecker.modules.network.CryptoApi
import javax.inject.Inject
import javax.inject.Named

class DetailsRepository @Inject constructor(@Named("CryptoApi") private val cryptoApi: CryptoApi) {

    suspend fun getHistoricalPrice(coinId: String, daysAgo: Int): HistoricalPricesDto {
        return cryptoApi.getCoinsMarketChart(coinId,"usd", daysAgo).body()!!
    }

    suspend fun getCoinDescription(coinId: String): String {
        return cryptoApi.getCoinsDescription(coinId, false).body()!!.description.text
    }

}