package hu.bme.cryptochecker.model.dto

import com.google.gson.annotations.SerializedName

// Contains general information and description about a cryptocurrency
data class CoinDescriptionDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("description")
    val description: DescriptionDto
)