package hu.bme.cryptochecker.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.db.HistoricalPrice
import hu.bme.cryptochecker.model.db.PriceHistory
import hu.bme.cryptochecker.model.db.converters.PriceHistoryConverter
import hu.bme.cryptochecker.model.db.relations.CryptocurrencyWithPriceHistories

@Dao
@TypeConverters(PriceHistoryConverter::class)
interface CryptocurrencyDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCryptocurrency(cryptocurrency: Cryptocurrency)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(priceHistory: PriceHistory)

    // Read
    @Query("SELECT * FROM coins")
    fun getAllCryptocurrencies(): LiveData<List<Cryptocurrency>>

    @Query("SELECT * FROM coins WHERE isFavourite = 1")
    fun getFavouriteCryptocurrencies(): LiveData<List<Cryptocurrency>>

    @Transaction
    @Query("SELECT * FROM coins")
    fun getCryptocurrencyWithPriceHistories(): LiveData<List<CryptocurrencyWithPriceHistories>>

    @Transaction
    @Query("SELECT * FROM coins WHERE id = :id")
    suspend fun getCryptocurrencyWithPriceHistories(id: String): LiveData<CryptocurrencyWithPriceHistories>

    @Transaction
    @Query("SELECT COUNT(*) FROM coins INNER JOIN history ON coins.id = history.cryptoId WHERE coins.id = :id AND history.daysAgo = :days")
    fun countCryptocurrencyHistory(id: String, days: Int): Int

    // Update
    @Update
    suspend fun updateCryptocurrency(cryptocurrency: Cryptocurrency)

    @Transaction
    @Query("UPDATE coins SET isFavourite = :favourite WHERE id = :id")
    suspend fun updateCryptocurrencyFavourite(id: String, favourite: Boolean)

    @Transaction
    @Query("UPDATE coins SET description = :description WHERE id = :id")
    suspend fun updateCryptocurrencyDescription(id: String, description: String)

    @Transaction
    @Query("UPDATE history SET history = :coinHistory WHERE cryptoId = :coinId AND daysAgo = :daysAgo")
    fun updateCryptocurrencyHistory(coinHistory: List<HistoricalPrice>, coinId: String, daysAgo: Int)

    // Delete
    @Delete
    suspend fun deleteCryptocurrency(cryptocurrency: Cryptocurrency)

    // Delete All
    @Query("DELETE FROM coins")
    suspend fun deleteAllCryptocurrencies()

}