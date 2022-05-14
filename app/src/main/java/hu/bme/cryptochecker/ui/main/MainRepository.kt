package hu.bme.cryptochecker.ui.main

import android.util.Log
import hu.bme.cryptochecker.model.dto.Cryptocurrency
import hu.bme.cryptochecker.modules.network.CryptoApi
import hu.bme.cryptochecker.modules.support.SupportedCurrencies
import javax.inject.Inject
import javax.inject.Named

class MainRepository @Inject constructor(@Named("CryptoApi") private val cryptoApi: CryptoApi) {

    suspend fun getCurrencies(): List<Cryptocurrency> {
        return cryptoApi.getCoinsMarkets("usd", convertSupportedCurrenciesToParameterList()).body()!!
    }

    private fun convertSupportedCurrenciesToParameterList(): String {
        val coins = SupportedCurrencies.values()
        var parameterList = ""

        for (i in coins.indices) {
            parameterList += if (i == coins.size - 1) {
                coins[i].id
            } else {
                coins[i].id + ","
            }
        }
        Log.d("apple", parameterList)
        return parameterList
    }

}