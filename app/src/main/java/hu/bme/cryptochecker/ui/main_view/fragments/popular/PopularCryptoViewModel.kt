package hu.bme.cryptochecker.ui.main_view.fragments.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.ui.main_view.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCryptoViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val popularCoins: LiveData<List<Cryptocurrency>> = mainRepository.currenciesCached

    fun addToFavourite(coinId: String){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.addCoinToFavourites(coinId)
        }
        Log.d("FAVOURITE", "$coinId added to favourites")
    }

    fun removeFavourite(coinId: String){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.removeCoinFromFavourites(coinId)
        }
        Log.d("FAVOURITE", "$coinId removed from favourites")
    }

}