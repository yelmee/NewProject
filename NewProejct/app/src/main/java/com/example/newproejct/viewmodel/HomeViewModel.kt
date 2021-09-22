package com.example.newproejct.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newproejct.data.banner.BannerRepository
import com.example.newproejct.data.category.CategoryRepository
import com.example.newproejct.data.shop.ShopRepository
import com.example.newproejct.data.MainRecyclerviewItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val bannerRepository: BannerRepository,
    private val shopRepository: ShopRepository, application: Application
) : BaseViewModel() {

    private val _categoryListItemLiveData = MutableLiveData<List<MainRecyclerviewItem.Category>>()
    val categoryListItemLiveData: LiveData<List<MainRecyclerviewItem.Category>>
        get() = _categoryListItemLiveData

    private val _bannerListItemLiveData = MutableLiveData<List<MainRecyclerviewItem.Banner>>()
    val bannerListItemLiveData: LiveData<List<MainRecyclerviewItem.Banner>>
        get() = _bannerListItemLiveData


    private val _shopListItemLiveData = MutableLiveData<List<MainRecyclerviewItem.Shop>>()
    val shopListItemLiveData: LiveData<List<MainRecyclerviewItem.Shop>>
        get() = _shopListItemLiveData

    init {
        getCategoryListItem()
        getBannerListItem()
        getShopListItem()
    }

    private fun getCategoryListItem() {
        val categoryItemsList = mutableListOf<MainRecyclerviewItem.Category>()
        compositeDisposable.add(
            categoryRepository.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(showProgress())
//                .doAfterTerminate(hideProgress())
                .doOnError { Log.d("jyl", it.toString()) }
                .subscribe { category ->
                    categoryItemsList.addAll(category.data.icons)
                    _categoryListItemLiveData.postValue(categoryItemsList)
                }
        )
    }

    private fun getBannerListItem() {
        val BannaerItemsList = mutableListOf<MainRecyclerviewItem.Banner>()
        compositeDisposable.add(
            bannerRepository.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(showProgress())
//                .doAfterTerminate(hideProgress())
                .doOnError { Log.d("jyl", it.toString()) }
                .subscribe { banner ->
                    BannaerItemsList.addAll(banner.data.mainBanners)
                    _bannerListItemLiveData.postValue(BannaerItemsList)
                }
        )
    }

    private fun getShopListItem() {
        val shopItemsList = mutableListOf<MainRecyclerviewItem.Shop>()
        compositeDisposable.add(
            shopRepository.getShop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(showProgress())
//                .doAfterTerminate(hideProgress())
                .doOnError { Log.d("jyl", it.toString()) }
                .subscribe { shop ->
                    shopItemsList.addAll(shop.data.shops)
                    _shopListItemLiveData.postValue(shopItemsList)
                }
        )
    }
}