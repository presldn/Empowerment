package com.presldn.empowerment.viewmodels

import androidx.lifecycle.MutableLiveData
import com.presldn.empowerment.models.Quote

class QuoteViewModel : BaseViewModel() {
    private val quoteAuthor = MutableLiveData<String>()
    private val quoteMessage = MutableLiveData<String>()

    fun bind(quote: Quote) {
        quoteAuthor.value = quote.author
        quoteMessage.value = quote.message
    }

    fun getQuoteAuthor(): MutableLiveData<String> = quoteAuthor

    fun getQuoteMessage(): MutableLiveData<String> = quoteMessage

}