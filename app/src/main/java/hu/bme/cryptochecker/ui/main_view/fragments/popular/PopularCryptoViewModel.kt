package hu.bme.cryptochecker.ui.main_view.fragments.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.ui.main_view.MainRepository
import javax.inject.Inject

@HiltViewModel
class PopularCryptoViewModel @Inject constructor(mainRepository: MainRepository) : ViewModel() {

    val popularCoins: LiveData<List<Cryptocurrency>> = mainRepository.currenciesCached

}