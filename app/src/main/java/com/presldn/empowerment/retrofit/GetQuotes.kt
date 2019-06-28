package com.presldn.empowerment.retrofit

import com.presldn.empowerment.models.Quote
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface GetQuotes {
    @GET("quotes")
    fun getQuotes(): Observable<List<Quote>>
}