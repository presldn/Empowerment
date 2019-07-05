package com.presldn.empowerment.networking.room

import androidx.room.*
import com.presldn.empowerment.models.Quote
import io.reactivex.Completable

@Dao
interface QuoteDao {
    @get:Query("SELECT * FROM quotes_table")
    val allQuotes: List<Quote>

    @get:Query("SELECT * FROM quotes_table WHERE favorite = 1")
    val allFavoriteQuotes: List<Quote>

    @Insert
    fun insertAll(vararg quotes: Quote)

    @Update
    fun update(quote: Quote): Completable

    @Delete
    fun delete(quote: Quote): Completable
}