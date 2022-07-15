package com.diponnkar.todotask.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Todo(
    @PrimaryKey var id: Int?,
    var title: String,
    var discription: String?
) : Parcelable
