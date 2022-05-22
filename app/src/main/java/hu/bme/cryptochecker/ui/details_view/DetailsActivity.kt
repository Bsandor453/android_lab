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
import hu.bme.cryptochecker.model.db.PriceHistory
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()
    private val args by navArgs<DetailsActivityArgs>()
    private lateinit var binding: ActivityDetailsBinding

    private var daysAgo = 1

    // Chart data
    private var coinHistories = emptyList<PriceHistory>()
    var firstTimeStamp: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set title
        supportActionBar?.title = args.selectedCoin.name + " details"

        // Set text views
        binding.nameText.text = args.selectedCoin.name
        val symbolText = "(${args.selectedCoin.symbol.uppercase(Locale.getDefault())})"
        binding.symbolText.text =  symbolText
        val priceText = "$ ${args.selectedCoin.price}"
        binding.priceText.text = priceText

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

        // Set timeframe buttons
        binding.oneDayButton.isChecked = true
        binding.oneDayButton.setOnClickListener {
            if(daysAgo != 1) {
                daysAgo = 1
                binding.priceChart.data = generateLineChartData(getHistoryByDaysAgo(coinHistories, daysAgo).history)
                // Invalidate to refresh chart
                binding.priceChart.invalidate()
                Log.d("TIMEFRAME CHANGE", daysAgo.toString())
            }
        }
        binding.sevenDayButton.setOnClickListener {
            if(daysAgo != 7) {
                daysAgo = 7
                binding.priceChart.data = generateLineChartData(getHistoryByDaysAgo(coinHistories, daysAgo).history)
                // Invalidate to refresh chart
                binding.priceChart.invalidate()
                Log.d("TIMEFRAME CHANGE", daysAgo.toString())
            }
        }
        binding.thirtyDayButton.setOnClickListener {
            if(daysAgo != 30) {
                daysAgo = 30
                binding.priceChart.data = generateLineChartData(getHistoryByDaysAgo(coinHistories, daysAgo).history)
                // Invalidate to refresh chart
                binding.priceChart.invalidate()
                Log.d("TIMEFRAME CHANGE", daysAgo.toString())
            }
        }

        // Format chart
        val chart = binding.priceChart
        chart.axisRight.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisLeft.valueFormatter = YAxisValueFormatter()
        chart.xAxis.valueFormatter = XAxisValueFormatter()
        chart.setNoDataText("Loading price information...")
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        // Get coin histories
        viewModel.getHistories(args.selectedCoin.id).observe(this) { histories ->
            if (histories.isNotEmpty()) {
                // Cache data
                coinHistories = histories
                // Set chart data
                binding.priceChart.data = generateLineChartData(getHistoryByDaysAgo(histories, daysAgo).history)
                // Invalidate to refresh chart
                binding.priceChart.invalidate()
            }
        }

    }

    private fun getHistoryByDaysAgo(priceHistories: List<PriceHistory>, daysAgo: Int): PriceHistory {
        for(priceHistory in priceHistories) {
            if (priceHistory.daysAgo == daysAgo) {
                return priceHistory
            }
        }
        return priceHistories[0]
    }

    private fun generateLineChartData(prices: List<HistoricalPrice>): LineData {
        val entries = mutableListOf<Entry>()

        firstTimeStamp = prices.first().timestamp

        for(priceData in prices) {
            entries.add(Entry(((priceData.timestamp - firstTimeStamp) / 1000).toFloat(),priceData.price.toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "Prices")
        return LineData(lineDataSet)
    }

    inner class XAxisValueFormatter: ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val originalValue = value.toInt() * 1000 + firstTimeStamp
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