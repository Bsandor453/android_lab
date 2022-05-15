package hu.bme.cryptochecker.mock.data

import androidx.lifecycle.LiveData
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.db.PriceHistory
import hu.bme.cryptochecker.model.db.relations.CryptocurrencyWithPriceHistories
import hu.bme.cryptochecker.persistence.CryptocurrencyDao

class MockCryptocurrencyDao: CryptocurrencyDao {
    override suspend fun addCryptocurrency(cryptocurrency: Cryptocurrency) {
        TODO("Not yet implemented")
    }

    override suspend fun addHistory(priceHistory: PriceHistory) {
        TODO("Not yet implemented")
    }

    override fun readAllData(): LiveData<List<Cryptocurrency>> {
        val testCryptocurrency =
            Cryptocurrency("btc",
                "Bitcoin",
                "BTC",
                1000.0,
                "url",
                "test",
                false
            )

        TODO("Not yet implemented")
    }

    override suspend fun getCryptocurrencyWithPriceHistories(id: String): List<CryptocurrencyWithPriceHistories> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCryptocurrency(cryptocurrency: Cryptocurrency) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCryptocurrency(cryptocurrency: Cryptocurrency) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCryptocurrencies() {
        TODO("Not yet implemented")
    }
}