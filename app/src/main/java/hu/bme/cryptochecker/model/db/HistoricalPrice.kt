package hu.bme.cryptochecker.model.db

data class HistoricalPrice (
    val timestamp: Long,
    val price: Double
)