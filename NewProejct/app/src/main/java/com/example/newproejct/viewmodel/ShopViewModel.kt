package com.example.newproejct.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newproejct.data.detailshop.DetailShopInfo
import com.example.newproejct.data.detailshop.DetailShopRepository
import com.example.newproejct.data.detailshop.MenuData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShopViewModel @Inject constructor(val detailShopRepository: DetailShopRepository) :
    BaseViewModel() {

    private var _requestRepresentMenu = MutableLiveData<List<MenuData.RepresentationMenu>>()
    val requestRepresentMenu: LiveData<List<MenuData.RepresentationMenu>> get() = _requestRepresentMenu

    private var _requestAllMenu = MutableLiveData<List<MenuData.GroupMenus>>()
    val requestAllMenu: LiveData<List<MenuData.GroupMenus>> get() = _requestAllMenu

    private var _requestShopInfo = MutableLiveData<DetailShopInfo>()
    val requestShopInfo: LiveData<DetailShopInfo> get() = _requestShopInfo

    var tvTitleItem = MutableLiveData<String>()
    var tvContentItem = MutableLiveData<String>()
    var tvPriceItem = MutableLiveData<String>()
    var ivImageItem = MutableLiveData<String>()

    init {
        getRepresentMenu()
        getShopInfo()
    }

    fun getRepresentMenu() {
        val representMenuList = mutableListOf<MenuData.RepresentationMenu>()
        val allMenuList = mutableListOf<MenuData.GroupMenus>()
        compositeDisposable.add(
            detailShopRepository.getDetailShop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError {}
                .subscribe { list ->
                    representMenuList.addAll(list.data.shop_menu.menuOrder.representationMenu)
                    _requestRepresentMenu.postValue(representMenuList)

                    allMenuList.addAll(list.data.shop_menu.menuOrder.groupMenus)
                    _requestAllMenu.postValue(allMenuList)
                }
        )
    }

    fun getShopInfo() {
        compositeDisposable.add(
            detailShopRepository.getDetailShop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError {}
                .subscribe { shop ->
                    _requestShopInfo.postValue(shop.data.shopInfo)
                }
        )

    }
}