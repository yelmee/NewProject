package com.example.newproejct.data.bag

import androidx.room.*

@Dao
interface BagDao {
    @Insert
    fun insert(bag: Bag)

    @Update
    fun update(bag: Bag)

    @Delete
    fun delete(bag: Bag)

    @Query("SELECT * FROM Bag")
    fun getBag(): List<Bag>
}