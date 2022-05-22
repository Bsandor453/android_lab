package hu.bme.cryptochecker.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.bme.cryptochecker.R
import hu.bme.cryptochecker.databinding.CustomRowBinding
import hu.bme.cryptochecker.model.db.Cryptocurrency
import hu.bme.cryptochecker.ui.main_view.fragments.popular.PopularCryptoFragmentDirections
import java.util.*

class ListAdapter(private val onSetFavouriteCallback: (coinId: String, favourite: Boolean) -> Unit):
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

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

        // Set details button onClickListener
        binding.buttonDetails.setOnClickListener {
            val action = PopularCryptoFragmentDirections.actionPopularCryptoFragmentToDetailsActivity(currentItem)
            holder.binding.root.findNavController().navigate(action)
        }

        // Set favourite button onClickListener and text -> set favourite if it's not favourite yet
        if (!currentItem.isFavourite) {
            binding.buttonFavourite.text = binding.root.context.getString(R.string.set_favourite_text)
            binding.buttonFavourite.setOnClickListener {
                // Call the given callback function -> Set favourite
                onSetFavouriteCallback(currentItem.id, true)
            }
        // Remove favourite if it's already favourite
        } else {
            binding.buttonFavourite.text = binding.root.context.getString(R.string.remove_favourite_text)
            binding.buttonFavourite.setOnClickListener {
                // Call the given callback function -> Remove favourite
                onSetFavouriteCallback(currentItem.id, false)
            }
        }

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