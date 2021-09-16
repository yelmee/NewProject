package com.example.newproejct.data

import com.example.newproejct.api.ApiService
import io.reactivex.Observable

class CategoryRepositoryImpl(val service: ApiService): CategoryRepository {
    override fun getCategory(): Observable<CategoryResponse> {
       return service.getCategories()
    }
}