package hu.bme.cryptochecker.ui.main_view

import androidx.lifecycle.LiveData
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.dto.CryptocurrencyDto
import hu.bme.cryptochecker.modules.network.CryptoApi
import hu.bme.cryptochecker.modules.network.support.SupportedCurrencies
import hu.bme.cryptochecker.persistence.CryptocurrencyDao
import javax.inject.Inject
import javax.inject.Named

class MainRepository @Inject constructor(
    @Named("CryptoApi") private val cryptoApi: CryptoApi,
    @Named("CryptoDao") private val cryptocurrencyDao: CryptocurrencyDao
    ) {

    val currenciesCached: LiveData<List<Cryptocurrency>> = cryptocurrencyDao.readAllData()

    suspend fun getCurrencies(): List<CryptocurrencyDto> {
        val coins = cryptoApi.getCoinsMarkets("usd", convertSupportedCurrenciesToParameterList()).body()!!

        // Add all coins to local db
        for(coin in coins) {
            addNewCoinLocal(coin)
        }

        return coins
    }

    private suspend fun addNewCoinLocal(coin: CryptocurrencyDto) {
        val newCoin = Cryptocurrency(
            coin.id,
            coin.name,
            coin.symbol,
            coin.price,
            coin.imageUrl,
            "",
            false)
        cryptocurrencyDao.addCryptocurrency(newCoin)
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
        return parameterList
    }

    suspend fun addCoinToFavourites(coinId: String) {
        cryptocurrencyDao.updateCryptocurrencyFavourite(coinId, true)
    }

    suspend fun removeCoinFromFavourites(coinId: String) {
        cryptocurrencyDao.updateCryptocurrencyFavourite(coinId, false)
    }

}