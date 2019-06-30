package com.presldn.empowerment.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

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

        binding.viewModel = viewModel
    }

}
