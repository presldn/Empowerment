package com.presldn.empowerment.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.presldn.empowerment.models.Quote

class QuoteViewModel : BaseViewModel() {
    private val quoteAuthor = MutableLiveData<String>()
    private val quoteMessage = MutableLiveData<String>()
    private val quoteFavorite = MutableLiveData<Boolean>()

    fun bind(quote: Quote) {
        quoteAuthor.value = quote.author
        quoteMessage.value = quote.message
        quoteFavorite.value = quote.favorite
    }

    fun getQuoteAuthor(): MutableLiveData<String> = quoteAuthor

    fun getQuoteMessage(): MutableLiveData<String> = quoteMessage

    fun isQuoteFavorite(): MutableLiveData<Boolean> = quoteFavorite

}