package hu.bme.cryptochecker.ui.main

import hu.bme.cryptochecker.model.dto.Cryptocurrency
import hu.bme.cryptochecker.model.dto.HistoricalPrices
import hu.bme.cryptochecker.modules.network.CryptoApi
import javax.inject.Inject
import javax.inject.Named

class MainRepository @Inject constructor(@Named("CryptoApi") private val cryptoApi: CryptoApi) {

    suspend fun getCurrencies(): List<Cryptocurrency> {
        return cryptoApi.getCoinsMarkets("usd").body()!!
    }

}