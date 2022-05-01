package hu.bme.cryptochecker.model

// Example cryptocurrency data
data class Cryptocurrency(
    val name: String,
    val price: Double,
    val referenceCurrency: String
)