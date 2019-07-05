package com.presldn.empowerment.viewmodels

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.presldn.empowerment.R
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.networking.room.QuoteDao
import com.presldn.empowerment.view.adapters.FavoritesListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class FavoriteListViewModel(
    private val quoteDao: QuoteDao,
    private val context: Context
) : BaseViewModel() {

    private val TAG = "FavoriteListViewModel"

    private lateinit var subscription: Disposable

    val favoriteQuotes: MutableLiveData<List<Quote>> = MutableLiveData()

    val emptyListMessageVisibility: MutableLiveData<Int> = MutableLiveData()
    val recyclerViewVisibility: MutableLiveData<Int> = MutableLiveData()

    private val favoritesListAdapter = FavoritesListAdapter(this, context)

    val favoriteRemoved: MutableLiveData<Int> = MutableLiveData()

    init {
        loadQuotes()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
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
        checkListSize()
    }

    fun getFavoritesListAdapter(): FavoritesListAdapter = favoritesListAdapter

    private fun checkListSize() {
        if (favoriteQuotes.value!!.isEmpty()) {
            emptyListMessageVisibility.value = View.VISIBLE
            recyclerViewVisibility.value = View.GONE
        }
        else {
            emptyListMessageVisibility.value = View.GONE
            recyclerViewVisibility.value = View.VISIBLE
        }
    }

    private fun onRetrieveQuotesFinish() {

    }

    private fun onRetrieveQuotesStart() {

    }

    fun onRemoveFavorite(quote: Quote) {
        subscription =  quoteDao.delete(quote)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveQuotesStart() }
            .doOnTerminate { onRetrieveQuotesFinish() }
            .subscribe(onDeleteSuccess())
    }

    private fun onDeleteSuccess(): Action {
        return Action {
            favoriteRemoved.value = R.string.favorite_removed
            loadQuotes()
        }
    }

}