package com.presldn.empowerment.networking.room

import androidx.annotation.WorkerThread
import com.presldn.empowerment.models.Quote

class QuoteRepository(private val quoteDao: QuoteDao) {
    val quotes = quoteDao.allQuotes

    @WorkerThread
    suspend fun delete(quote: Quote) {
        quoteDao.delete(quote)
    }
}