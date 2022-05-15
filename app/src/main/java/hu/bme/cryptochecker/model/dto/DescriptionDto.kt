package hu.bme.cryptochecker.model.dto

import com.google.gson.annotations.SerializedName

// Contains the HTML string of the english description of a cryptocurrency
data class DescriptionDto (
    @SerializedName("en")
    val text: String
)