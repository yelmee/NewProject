package com.example.newproejct.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newproejct.MyApplication
import com.example.newproejct.data.bag.Bag
import com.example.newproejct.data.bag.BagDataBase
import com.example.newproejct.data.detailshop.MenuData

class BagViewModel: BaseViewModel() {
    private var _requestBagList = MutableLiveData<List<Bag>>()
    val requestBagList: LiveData<List<Bag>> get() = _requestBagList

    init {
        getBag()
    }
    private fun getBag() {
        val db = BagDataBase.getInstance(MyApplication.getAppContext())
        val bagData =  db!!.bagDao().getBag()
        _requestBagList.postValue(bagData)
    }

     fun insertBag(item: MenuData.RepresentationMenu) {
        val bag = Bag(item.name, item.menuPrices[0].price, 1)
        val db = BagDataBase.getInstance(MyApplication.getAppContext())
        db!!.bagDao().insert(bag)
    }
}