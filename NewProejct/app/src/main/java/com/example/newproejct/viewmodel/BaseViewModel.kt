package com.example.newproejct.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.newproejct.ViewInteractor
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<VI: ViewInteractor>(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    protected val compositeDisposable = CompositeDisposable()

    var viewInteractor: VI? = null
    set

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}