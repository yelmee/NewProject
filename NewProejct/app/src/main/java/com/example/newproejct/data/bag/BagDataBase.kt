package com.example.newproejct.data.bag

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bag::class], version = 1)
abstract class BagDataBase: RoomDatabase() {
    abstract fun bagDao(): BagDao

    companion object{
        private var instance: BagDataBase ?= null

        @Synchronized
        fun getInstance(context: Context): BagDataBase? {
            if (instance == null) {
                synchronized(BagDataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BagDataBase::class.java,
                        "bag_database"
                    ).build()
                }
            }
            return instance
        }
    }
}