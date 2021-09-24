package com.example.newproejct.data.bag

import androidx.lifecycle.LiveData
import com.example.newproejct.data.detailshop.MenuData
import io.reactivex.Observable
import javax.inject.Inject

class BagRepository @Inject constructor(private val bagDao: BagDao) {
    fun createBag(item: Bag) {
        bagDao.insert(item)
    }

    fun getBag(): LiveData<List<Bag>> {
        return bagDao.getBag()
    }
}