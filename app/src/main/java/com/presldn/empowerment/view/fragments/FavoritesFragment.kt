package com.presldn.empowerment.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.FragmentFavoritesBinding
import com.presldn.empowerment.networking.dagger.injection.ViewModelFactory
import com.presldn.empowerment.viewmodels.FavoriteListViewModel

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoritesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 *
 */
class FavoritesFragment : Fragment() {

    private lateinit var viewModel: FavoriteListViewModel

    private lateinit var binding: FragmentFavoritesBinding

    private var snackbar: Snackbar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory(activity!!)).get(FavoriteListViewModel::class.java)

        viewModel.favoriteRemoved.observe(this, Observer { message ->
            if(message != null) showMessage(message)
        })

        binding.viewModel = viewModel
    }

    private fun showMessage(message: Int) {
        snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar?.show()
    }

}
