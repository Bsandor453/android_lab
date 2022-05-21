package hu.bme.cryptochecker.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.db.PriceHistory
import hu.bme.cryptochecker.model.db.relations.CryptocurrencyWithPriceHistories

@Dao
interface CryptocurrencyDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCryptocurrency(cryptocurrency: Cryptocurrency)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(priceHistory: PriceHistory)

    // Read
    @Query("SELECT * FROM coins")
    fun readAllData(): LiveData<List<Cryptocurrency>>

    @Transaction
    @Query("SELECT * FROM coins WHERE id = :id")
    suspend fun getCryptocurrencyWithPriceHistories(id: String): List<CryptocurrencyWithPriceHistories>

    // Update
    @Update
    suspend fun updateCryptocurrency(cryptocurrency: Cryptocurrency)

    @Transaction
    @Query("UPDATE coins SET isFavourite = :favourite WHERE id = :id")
    suspend fun updateCryptocurrencyFavourite(id: String, favourite: Boolean)

    // Delete
    @Delete
    suspend fun deleteCryptocurrency(cryptocurrency: Cryptocurrency)

    // Delete All
    @Query("DELETE FROM coins")
    suspend fun deleteAllCryptocurrencies()

}