package hu.bme.cryptochecker.ui.details_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.db.PriceHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) : ViewModel() {

    fun getHistories(coinId: String): LiveData<List<PriceHistory>> {
        return Transformations.map(detailsRepository.getCurrencyWithHistory(coinId)) {
            it.priceHistories
        }
    }

    fun getDescription(coinId: String): LiveData<String> {
        return Transformations.map(detailsRepository.getCurrencyWithHistory(coinId)) {
            it.cryptocurrency.description
        }
    }

    fun retrieveCoinData(coinId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Fetch and save data
            detailsRepository.fetchAndSaveCoinData(coinId)
        }
    }

}