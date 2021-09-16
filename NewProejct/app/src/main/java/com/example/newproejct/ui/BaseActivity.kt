package com.example.newproejct.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B: ViewDataBinding>(
    @LayoutRes val layoutId: Int
): AppCompatActivity() {
    lateinit var binding: B
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId )
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}