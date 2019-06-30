package com.presldn.empowerment.networking.dagger.injection

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.presldn.empowerment.networking.room.AppDatabase
import com.presldn.empowerment.viewmodels.FavoriteListViewModel
import com.presldn.empowerment.viewmodels.QuoteListViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "quotes").build()
            @Suppress("UNCHECKED_CAST")
            return QuoteListViewModel(db.quoteDao()) as T
        } else if(modelClass.isAssignableFrom(FavoriteListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "quotes").build()
            @Suppress("UNCHECKED_CAST")
            return FavoriteListViewModel(db.quoteDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}