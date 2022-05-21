package hu.bme.cryptochecker.ui.details_view

import androidx.lifecycle.LiveData
import hu.bme.cryptochecker.model.db.HistoricalPrice
import hu.bme.cryptochecker.model.db.PriceHistory
import hu.bme.cryptochecker.model.db.relations.CryptocurrencyWithPriceHistories
import hu.bme.cryptochecker.model.dto.HistoricalPricesDto
import hu.bme.cryptochecker.modules.network.CryptoApi
import hu.bme.cryptochecker.persistence.CryptocurrencyDao
import javax.inject.Inject
import javax.inject.Named

class DetailsRepository @Inject constructor(
    @Named("CryptoApi") private val cryptoApi: CryptoApi,
    @Named("CryptoDao") private val cryptocurrencyDao: CryptocurrencyDao
) {

    fun getCurrencyWithHistory(coinId: String): LiveData<CryptocurrencyWithPriceHistories> {
        return cryptocurrencyDao.getCryptocurrencyWithPriceHistories(coinId)
    }

    suspend fun fetchAndSaveCoinData(coinId: String) {
        // Get the coin's description and save it
        val description = getCoinDescriptionFromApi(coinId)
        cryptocurrencyDao.updateCryptocurrencyDescription(coinId, description)

        // Get the 1 day price history
        savePriceHistoryLocal(1, coinId)
    }

    private suspend fun getHistoricalPriceFromApi(coinId: String, daysAgo: Int): HistoricalPricesDto {
        return cryptoApi.getCoinsMarketChart(coinId,"usd", daysAgo).body()!!
    }

    private suspend fun getCoinDescriptionFromApi(coinId: String): String {
        return cryptoApi.getCoinsDescription(coinId, false).body()!!.description.text
    }

    private suspend fun savePriceHistoryLocal(days: Int, coinId: String) {
        val history = getHistoricalPriceFromApi(coinId, days)
        val historicalPriceList = mutableListOf<HistoricalPrice>()
        for (historicalPrice in history.prices) {
            val newHistoricalPrice =
                HistoricalPrice(historicalPrice[0].toInt(), historicalPrice[1].toDouble())
            historicalPriceList.add(newHistoricalPrice)
        }
        val newHistory = PriceHistory(
            0, // Auto-generated
            days,
            historicalPriceList,
            coinId
        )
        cryptocurrencyDao.addHistory(newHistory)
    }

}