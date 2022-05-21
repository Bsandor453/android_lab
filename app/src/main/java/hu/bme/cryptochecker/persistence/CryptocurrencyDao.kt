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
    fun getAllCryptocurrencies(): LiveData<List<Cryptocurrency>>

    @Query("SELECT * FROM coins WHERE isFavourite = 1")
    fun getFavouriteCryptocurrencies(): LiveData<List<Cryptocurrency>>

    @Transaction
    @Query("SELECT * FROM coins")
    fun getCryptocurrencyWithPriceHistories(): LiveData<List<CryptocurrencyWithPriceHistories>>

    @Transaction
    @Query("SELECT * FROM coins WHERE id = :id")
    fun getCryptocurrencyWithPriceHistories(id: String): LiveData<CryptocurrencyWithPriceHistories>

    // Update
    @Update
    suspend fun updateCryptocurrency(cryptocurrency: Cryptocurrency)

    @Transaction
    @Query("UPDATE coins SET isFavourite = :favourite WHERE id = :id")
    suspend fun updateCryptocurrencyFavourite(id: String, favourite: Boolean)

    @Transaction
    @Query("UPDATE coins SET description = :description WHERE id = :id")
    suspend fun updateCryptocurrencyDescription(id: String, description: String)

    // Delete
    @Delete
    suspend fun deleteCryptocurrency(cryptocurrency: Cryptocurrency)

    // Delete All
    @Query("DELETE FROM coins")
    suspend fun deleteAllCryptocurrencies()

}