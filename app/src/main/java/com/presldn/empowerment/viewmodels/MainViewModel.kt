package com.presldn.empowerment.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.presldn.empowerment.R
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.retrofit.QuotesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    private val TAG = "MainViewModel"

    @Inject
    lateinit var quotesApi: QuotesApi

    private lateinit var subscription: Disposable

    val quotes: MutableLiveData<List<Quote>> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val fragmentVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadQuotes() }


    init {
        loadQuotes()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadQuotes() {
        subscription = quotesApi.getQuotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveQuotesStart() }
            .doOnTerminate { onRetrieveQuotesFinish() }
            .subscribe(
                {result -> onRetrieveQuotesSuccess(result)},
                {onRetrieveQuotesError()}
            )
    }

    private fun onRetrieveQuotesStart(){
        loadingVisibility.value = View.VISIBLE
        fragmentVisibility.value = View.GONE
        errorMessage.value = null
    }

    private fun onRetrieveQuotesFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveQuotesSuccess(result: List<Quote>) {
        fragmentVisibility.value = View.VISIBLE
        Log.d(TAG, "quotes: $result")
        quotes.value = result
    }

    private fun onRetrieveQuotesError(){
        errorMessage.value = R.string.quotes_error
    }

}