package com.presldn.empowerment.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "quotes_table")
@Parcelize
data class Quote(val author: String, val message: String) : Parcelable {
    @PrimaryKey(autoGenerate = true) var id = 0

    @ColumnInfo(name = "favorite") var favorite: Boolean = false
}