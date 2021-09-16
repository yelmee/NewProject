package com.example.newproejct.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproejct.api.ApiService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

interface CategoryRepository{
    fun getCategory(): Observable<CategoryResponse>
}