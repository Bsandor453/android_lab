package hu.bme.cryptochecker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.Cryptocurrency
import hu.bme.cryptochecker.modules.network.CryptoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val testResponse: MutableLiveData<Response<Array<Cryptocurrency>>> = MutableLiveData()

    fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.getCurrencies()
            testResponse.postValue(response)
        }
    }

}