package com.presldn.empowerment.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.networking.room.QuoteDao
import com.presldn.empowerment.view.adapters.FavoritesListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteListViewModel(private val quoteDao: QuoteDao) : BaseViewModel() {

    private val TAG = "FavoriteListViewModel"

    private lateinit var subscription: Disposable

    val favoriteQuotes: MutableLiveData<List<Quote>> = MutableLiveData()

    val favoritesListAdapter: FavoritesListAdapter = FavoritesListAdapter()

    init {
        loadQuotes()
    }

    private fun loadQuotes() {
        subscription = Observable.fromCallable { quoteDao.allFavoriteQuotes }
            .concatMap { dbQuotesList ->
                Observable.just(dbQuotesList)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveQuotesStart() }
            .doOnTerminate { onRetrieveQuotesFinish() }
            .subscribe(
                { result -> onRetrieveQuotesSuccess(result) },
                { onRetrieveQuotesError() }
            )
    }

    private fun onRetrieveQuotesError() {

    }

    private fun onRetrieveQuotesSuccess(result: List<Quote>) {
        Log.d(TAG, "favoriteQuotes: $result")
        favoriteQuotes.value = result
        favoritesListAdapter.updateFavorites(result)
    }

    private fun onRetrieveQuotesFinish() {

    }

    private fun onRetrieveQuotesStart() {

    }

}