package com.presldn.empowerment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_quote_table")
data class Quote(val author: String, val message: String, val keywords: List<String>) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}