package com.presldn.empowerment.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.presldn.empowerment.R
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.retrofit.RetrofitClient
import com.presldn.empowerment.viewmodels.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var mainViewModel: MainViewModel

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_quotes -> {
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

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


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

    fun handleResponse(quoteList: List<Quote>) {
        Log.d(TAG, "list of quotes: $quoteList")

//        quotes = quoteList

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
