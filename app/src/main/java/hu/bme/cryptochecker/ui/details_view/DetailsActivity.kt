package hu.bme.cryptochecker.ui.details_view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.databinding.ActivityDetailsBinding

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()
    private val args by navArgs<DetailsActivityArgs>()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Test set text
        binding.detailsText.text = args.selectedCoin.name

        // Test get history
        viewModel.getHistoryData()
        viewModel.historyData.observe(this) { history ->
            Log.d("Example history", history.prices[0].toString())
        }

        // Test get description
        viewModel.getDescription()
        viewModel.description.observe(this) { description ->
            Log.d("Example description", description)
        }
    }

}