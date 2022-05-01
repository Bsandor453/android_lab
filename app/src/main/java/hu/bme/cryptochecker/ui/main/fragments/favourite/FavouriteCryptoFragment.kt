package hu.bme.cryptochecker.ui.main.fragments.favourite

import android.content.Intent
import hu.bme.cryptochecker.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import hu.bme.cryptochecker.ui.details.DetailsActivity

class FavouriteCryptoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
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