package hu.bme.cryptochecker.ui.details

import android.util.Log
import hu.bme.cryptochecker.model.dto.Description
import hu.bme.cryptochecker.model.dto.HistoricalPrices
import hu.bme.cryptochecker.modules.network.CryptoApi
import javax.inject.Inject
import javax.inject.Named

class DetailsRepository @Inject constructor(@Named("CryptoApi") private val cryptoApi: CryptoApi) {

    suspend fun getHistoricalPrice(coinId: String, daysAgo: Int): HistoricalPrices {
        return cryptoApi.getCoinsMarketChart(coinId,"usd", daysAgo).body()!!
    }

    suspend fun getCoinDescription(coinId: String): String {
        return cryptoApi.getCoinsDescription(coinId, false).body()!!.description.text
    }

}