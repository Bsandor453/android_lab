package hu.bme.cryptochecker.ui.main_view.fragments.favourite

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
class FavouriteCryptoViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val favouriteCoins: LiveData<List<Cryptocurrency>> = mainRepository.favouriteCurrencies

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
