package hu.bme.cryptochecker.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.db.PriceHistory

@Dao
interface HistoryDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(history: PriceHistory)

    // Read
    @Query("SELECT * FROM history ORDER BY id ASC")
    fun readAllData(): LiveData<List<PriceHistory>>

    // Update
    @Update
    suspend fun updateCryptocurrency(history: PriceHistory)

    // Delete
    @Delete
    suspend fun deleteCryptocurrency(history: PriceHistory)

    // Delete All
    @Query("DELETE FROM history")
    suspend fun deleteAllCryptocurrencies()

}