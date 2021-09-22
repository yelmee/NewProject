package com.example.newproejct.data.category

import io.reactivex.Observable

interface CategoryRepository{
    fun getCategory(): Observable<CategoryResponse>
}