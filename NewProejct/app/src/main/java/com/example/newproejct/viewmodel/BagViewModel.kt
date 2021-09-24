package com.example.newproejct.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newproejct.MyApplication
import com.example.newproejct.data.bag.Bag
import com.example.newproejct.data.bag.BagDataBase
import com.example.newproejct.data.bag.BagRepository
import com.example.newproejct.data.detailshop.MenuData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BagViewModel @Inject constructor(private val bagRepository: BagRepository) : BaseViewModel() {
//    private var _requestBagList = MutableLiveData<List<Bag>>()
//    val requestBagList: LiveData<List<Bag>> get() = _requestBagList

    init {
        getBag()
    }

    fun addBag(item: Bag) {
        bagRepository.createBag(item)
    }

    fun getBag(): LiveData<List<Bag>> {
        return bagRepository.getBag()
    }
}