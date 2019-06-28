package com.presldn.empowerment.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favourite_quote_table")
data class Quote(val author: String, val message: String, val keywords: List<String>) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}