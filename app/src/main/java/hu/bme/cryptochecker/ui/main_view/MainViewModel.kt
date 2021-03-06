package hu.bme.cryptochecker.ui.main_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.model.dto.CryptocurrencyDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val cryptocurrencies: MutableLiveData<List<CryptocurrencyDto>> = MutableLiveData()
    val cryptocurrenciesCached: LiveData<List<Cryptocurrency>> = mainRepository.currencies

    fun getCurrenciesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mainRepository.getCurrenciesFromApi()
            cryptocurrencies.postValue(list)
        }
    }

}