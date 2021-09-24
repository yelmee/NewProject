package com.example.newproejct.data.bag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bag(
    var title: String,
    var price: String,
    var count: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}