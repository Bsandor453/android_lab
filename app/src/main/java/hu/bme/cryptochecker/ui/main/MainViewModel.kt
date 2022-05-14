package hu.bme.cryptochecker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.dto.Cryptocurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val cryptocurrencies: MutableLiveData<List<Cryptocurrency>> = MutableLiveData()

    fun getCurrenciesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mainRepository.getCurrencies()
            cryptocurrencies.postValue(list)
        }
    }

}