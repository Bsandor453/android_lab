package hu.bme.cryptochecker.ui.main_view.fragments.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.cryptochecker.R
import hu.bme.cryptochecker.databinding.FragmentPopularBinding
import hu.bme.cryptochecker.ui.adapter.ListAdapter
import hu.bme.cryptochecker.ui.details_view.DetailsActivity

@AndroidEntryPoint
class PopularCryptoFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    // Inject ViewModel
    private val viewModel: PopularCryptoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPopularBinding.inflate(inflater, container, false)

        /* Set RecyclerView */

        // Create adapter with favourite button callback
        val coinListAdapter = ListAdapter { coinId, favourite ->
            if (favourite) {
                viewModel.addToFavourite(coinId)
            } else {
                viewModel.removeFavourite(coinId)
            }
        }
        val coinList = binding.coinList
        coinList.adapter = coinListAdapter
        coinList.layoutManager = LinearLayoutManager(requireContext())

        // Set RecyclerView Data
        viewModel.popularCoins.observe(viewLifecycleOwner) { coins ->
            coinListAdapter.setData(coins)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create navigation to details view
        view.findViewById<Button>(R.id.details_button)?.setOnClickListener {
            val intent = Intent(activity, DetailsActivity::class.java)
            activity?.startActivity(intent)
        }
    }

}