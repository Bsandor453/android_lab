package hu.bme.cryptochecker.model.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.db.PriceHistory

data class CryptocurrencyWithPriceHistories (
        @Embedded
        val cryptocurrency: Cryptocurrency,
        @Relation(parentColumn = "id", entityColumn = "cryptoId")
        val priceHistories: List<PriceHistory>
)