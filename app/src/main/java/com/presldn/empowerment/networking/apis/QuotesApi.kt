package com.presldn.empowerment.networking.apis

import com.presldn.empowerment.models.Quote
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface QuotesApi {
    @GET("quotes")
    fun getQuotes(): Observable<List<Quote>>
}