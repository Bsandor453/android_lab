package hu.bme.cryptochecker.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.dto.Cryptocurrency
import hu.bme.cryptochecker.model.dto.HistoricalPrices
import hu.bme.cryptochecker.ui.main.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) : ViewModel() {

    private val coinId = "bitcoin"
    val historyData: MutableLiveData<HistoricalPrices> = MutableLiveData()

    fun getHistoryData() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = detailsRepository.getHistoricalPrice(coinId, 30)
            historyData.postValue(list)
        }
    }

}