package hu.bme.cryptochecker.ui.details_view

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.databinding.ActivityDetailsBinding
import hu.bme.cryptochecker.model.db.HistoricalPrice
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()
    private val args by navArgs<DetailsActivityArgs>()
    private lateinit var binding: ActivityDetailsBinding

    // Used for X axis label calculation
    var firstTimeStamp: Long = 0

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

        // Format chart
        val chart = binding.priceChart
        chart.axisRight.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        //chart.xAxis.setDrawGridLines(false)
        //chart.axisLeft.setDrawGridLines(false)
        chart.axisLeft.valueFormatter = YAxisValueFormatter()
        chart.xAxis.valueFormatter = XAxisValueFormatter()
        chart.setNoDataText("Loading price information...")
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        // Get coin histories
        viewModel.getHistories(args.selectedCoin.id).observe(this) { histories ->
            if (histories.isNotEmpty()) {
                // Set data
                binding.priceChart.data = generateLineChartData(histories[0].history)
                // Invalidate to refresh chart
                binding.priceChart.invalidate()
            }
        }

    }

    private fun generateLineChartData(prices: List<HistoricalPrice>): LineData {
        val entries = mutableListOf<Entry>()

        firstTimeStamp = prices.first().timestamp

        Log.d("asd2", firstTimeStamp.toString())

        for(priceData in prices) {
            entries.add(Entry(((priceData.timestamp - firstTimeStamp) / 1000).toFloat(),priceData.price.toFloat()))
            Log.d("HM", ((priceData.timestamp - firstTimeStamp) / 1000).toFloat().toString())
        }

        val lineDataSet = LineDataSet(entries, "Prices")
        return LineData(lineDataSet)
    }

    inner class XAxisValueFormatter: ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val originalValue = value.toInt() * 1000 + firstTimeStamp
            Log.d("ORIGINAL", originalValue.toString())
            Log.d("asd1", value.toString() + " " + value.toInt().toString() + " " + firstTimeStamp)
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = Date(originalValue)
            return sdf.format(date)
        }
    }

    class YAxisValueFormatter: ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return value.toLong().toString() + " $"
        }
    }

}