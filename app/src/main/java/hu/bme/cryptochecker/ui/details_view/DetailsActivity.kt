package hu.bme.cryptochecker.ui.details_view

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
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

        // Test test data
        // Load image
        Glide.with(binding.root)
            .load(args.selectedCoin.imageUrl)
            .into(binding.coinImage)

        // Retrieve coin data
        viewModel.retrieveCoinData(args.selectedCoin.id)

        // Get coin description
        viewModel.getDescription(args.selectedCoin.id).observe(this) { descriptionHtml ->
            binding.descriptionText.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(descriptionHtml, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(descriptionHtml)
            }
        }

        // Get coin histories
        viewModel.getHistories(args.selectedCoin.id).observe(this) { histories ->
            if (histories.isNotEmpty()) {
                binding.historyText.text = histories[0].daysAgo.toString()
            }
        }

    }

}