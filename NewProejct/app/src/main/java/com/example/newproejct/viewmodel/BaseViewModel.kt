package com.example.newproejct.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.newproejct.ViewInteractor
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(): ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}