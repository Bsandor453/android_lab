package hu.bme.cryptochecker.ui.details_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.R

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    // ViewModel inject
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

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