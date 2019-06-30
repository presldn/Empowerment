package com.presldn.empowerment.networking.room

import androidx.room.*
import com.presldn.empowerment.models.Quote

@Dao
interface QuoteDao {
    @get:Query("SELECT * FROM quotes_table")
    val allQuotes: List<Quote>

    @Insert
    fun insertAll(vararg quotes: Quote)

    @Delete
    fun delete(quote: Quote)

    @Update
    fun update(quote: Quote)
}