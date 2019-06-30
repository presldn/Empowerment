package com.presldn.empowerment.networking.dagger.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.presldn.empowerment.networking.room.AppDatabase
import com.presldn.empowerment.viewmodels.MainViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "quotes").build()
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(db.quoteDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}