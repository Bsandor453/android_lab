package hu.bme.cryptochecker.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hu.bme.cryptochecker.model.db.converters.PriceHistoryConverter

@Entity(tableName = "history")
@TypeConverters(PriceHistoryConverter::class)
data class PriceHistory (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val daysAgo: Int,
    val history: List<HistoricalPrice>,
    val cryptoId: String
)

