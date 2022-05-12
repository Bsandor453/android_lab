package hu.bme.cryptochecker.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.R
import hu.bme.cryptochecker.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    @Inject
    @Named("DummyPersistenceService")
    lateinit var persistenceService: String

    // ViewModel inject
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

}