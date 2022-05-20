package hu.bme.cryptochecker.ui.main_view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.R
import hu.bme.cryptochecker.databinding.ActivityMainBinding
import hu.bme.cryptochecker.ui.main_view.fragments.favourite.FavouriteCryptoFragment
import hu.bme.cryptochecker.ui.main_view.fragments.popular.PopularCryptoFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // ViewModel inject
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Popular cryptocurrencies is the default
        replaceFragment(PopularCryptoFragment())

        binding.popularButton.setOnClickListener {
            replaceFragment(PopularCryptoFragment())
        }
        binding.favouriteButton.setOnClickListener {
            replaceFragment(FavouriteCryptoFragment())
        }

        // Test get currencies list
        viewModel.getCurrenciesList()
        viewModel.cryptocurrencies.observe(this) { coins ->
            coins.forEachIndexed { index, coin ->
                Log.d("Coin", coin.name + ", Index: " + index)
            }
        }

        // Test get currencies cached
        viewModel.cryptocurrenciesCached.observe(this) { coins ->
            coins.forEachIndexed { index, coin ->
                Log.d("Coin cached", coin.name + ", Index: " + index)
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}