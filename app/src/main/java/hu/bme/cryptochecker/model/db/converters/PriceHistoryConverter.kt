package hu.bme.cryptochecker.model.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hu.bme.cryptochecker.model.db.HistoricalPrice
import java.lang.reflect.Type


class PriceHistoryConverter {

    @TypeConverter
    fun fromHistoricalPriceList(list: List<HistoricalPrice>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<HistoricalPrice>>(){}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toHistoricalPriceList(listString: String): List<HistoricalPrice> {
        val gson = Gson()
        val type = object : TypeToken<List<HistoricalPrice>>(){}.type
        return gson.fromJson(listString, type)
    }

}