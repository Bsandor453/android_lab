package hu.bme.cryptochecker.model.dto

import com.google.gson.annotations.SerializedName

// Contains general information about a cryptocurrency
data class Cryptocurrency (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("current_price")
    val price: Double,
    @SerializedName("image")
    val imageUrl: String
)