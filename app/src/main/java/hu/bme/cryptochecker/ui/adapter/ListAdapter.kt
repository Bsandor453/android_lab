package hu.bme.cryptochecker.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.bme.cryptochecker.databinding.CustomRowBinding
import hu.bme.cryptochecker.model.db.Cryptocurrency
import java.util.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var cryptocurrencyList = emptyList<Cryptocurrency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        val currentItem = cryptocurrencyList[position]

        /* Set list data */

        // Set name
        binding.nameText.text = currentItem.name

        // Set symbol
        val symbolText = "(${currentItem.symbol.uppercase(Locale.getDefault())})"
        binding.symbolText.text = symbolText

        // Set price
        val priceText = "$ ${currentItem.price}"
        binding.priceText.text = priceText

        // Load image
        Glide.with(binding.rowLayout)
            .load(currentItem.imageUrl)
            .into(binding.coinImage)
    }

    override fun getItemCount(): Int {
        return cryptocurrencyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cryptocurrencyList: List<Cryptocurrency>) {
        this.cryptocurrencyList = cryptocurrencyList
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root)

}