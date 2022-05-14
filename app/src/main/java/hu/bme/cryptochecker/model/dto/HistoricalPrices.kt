package hu.bme.cryptochecker.model.dto

import com.google.gson.annotations.SerializedName

// Contains historical price information about a cryptocurrency
// The inner list is a fixed 2-element array
// Inner list format: [timestamp, price]
// timestamp: UNIX timestamp
// price: The price in USD at the given time
data class HistoricalPrices (
    @SerializedName("prices")
    val prices: List<List<Number>>
)