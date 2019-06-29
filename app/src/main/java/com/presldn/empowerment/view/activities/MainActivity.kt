package com.presldn.empowerment.view.activities

import android.net.Uri
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.ActivityMainBinding
import com.presldn.empowerment.view.fragments.QuoteSlidePagerFragment
import com.presldn.empowerment.view.fragments.QuotesFragment
import com.presldn.empowerment.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), QuoteSlidePagerFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val TAG = "MainActivity"

    private lateinit var viewModel: MainViewModel

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
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

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

    fun loadFragment(newFragment: Fragment) {
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
