package hu.bme.cryptochecker.ui.main

import hu.bme.cryptochecker.model.Cryptocurrency
import hu.bme.cryptochecker.modules.network.CryptoApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class MainRepository @Inject constructor(@Named("CryptoApi") private val cryptoApi: CryptoApi) {

    suspend fun getCurrencies(): Response<Array<Cryptocurrency>> {
        return cryptoApi.getCoinsMarkets("usd")
    }

}