package com.presldn.empowerment.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
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

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

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
                {onRetrieveQuotesSuccess()},
                {onRetrieveQuotesError()}
            )
    }

    private fun onRetrieveQuotesStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveQuotesFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveQuotesSuccess(){

    }

    private fun onRetrieveQuotesError(){

    }

}