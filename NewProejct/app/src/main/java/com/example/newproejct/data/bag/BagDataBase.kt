package com.example.newproejct.data.bag

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bag::class], version = 1)
abstract class BagDataBase: RoomDatabase() {
    abstract fun bagDao(): BagDao
}