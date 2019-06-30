package com.presldn.empowerment.view.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.ActivityMainBinding
import com.presldn.empowerment.databinding.FragmentQuoteSlidePagerBinding
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.networking.dagger.injection.ViewModelFactory
import com.presldn.empowerment.view.fragments.FavoritesFragment
import com.presldn.empowerment.view.fragments.QuoteSlidePagerFragment
import com.presldn.empowerment.view.fragments.QuotesFragment
import com.presldn.empowerment.viewmodels.QuoteListViewModel

class MainActivity : AppCompatActivity(), QuoteSlidePagerFragment.OnQuoteInteractionListener {




    private val TAG = "MainActivity"

    private lateinit var viewModel: QuoteListViewModel

    private lateinit var binding: ActivityMainBinding

    private var errorSnackbar: Snackbar? = null



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_quotes -> {
                loadFragment(QuotesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                Log.d(TAG, "load the ting ===================== ")
                loadFragment(FavoritesFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onShareInteraction(quote: Quote?) {

    }

    override fun onFavoriteInteraction(
        quote: Quote?,
        binding: FragmentQuoteSlidePagerBinding
    ) {
        viewModel.toggleFavorite(quote, binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(QuoteListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        loadFragment(QuotesFragment())
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun loadFragment(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.fragment_container, newFragment)
            addToBackStack(null)
        }

        // Commit the transaction
        transaction.commit()
    }

}
