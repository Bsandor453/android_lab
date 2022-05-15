package hu.bme.cryptochecker.model.db

data class HistoricalPrice (
    val timestamp: Int,
    val price: Double
)