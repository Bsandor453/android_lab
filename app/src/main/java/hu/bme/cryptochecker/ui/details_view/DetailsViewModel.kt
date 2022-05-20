package hu.bme.cryptochecker.ui.details_view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.dto.HistoricalPricesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) : ViewModel() {

    private val coinId = "bitcoin"
    val historyData: MutableLiveData<HistoricalPricesDto> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()

    fun getHistoryData() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = detailsRepository.getHistoricalPrice(coinId, 30)
            historyData.postValue(list)
        }
    }

    fun getDescription() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = detailsRepository.getCoinDescription(coinId)
            Log.d("ASD", data.length.toString())
            description.postValue(data)
        }
    }

}