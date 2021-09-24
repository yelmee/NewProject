package com.example.newproejct.data.bag

import androidx.lifecycle.LiveData
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
    fun getBag(): LiveData<List<Bag>>
}