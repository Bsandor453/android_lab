package hu.bme.cryptochecker.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class Cryptocurrency (

    // General data
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val symbol: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val isFavourite: Boolean

)