package hu.bme.cryptochecker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.R
import hu.bme.cryptochecker.databinding.ActivityMainBinding
import hu.bme.cryptochecker.ui.main.fragments.favourite.FavouriteCryptoFragment
import hu.bme.cryptochecker.ui.main.fragments.popular.PopularCryptoFragment
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    @Named("DummyNetworkService")
    lateinit var networkService: String

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
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}