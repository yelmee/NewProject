package com.example.newproejct.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinApplication.context = this
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@KoinApplication)
            modules(
                apiModule,
                viewModelModule,
                modelPart
            )
        }
    }


    companion object{
        lateinit var context: Context

        fun getAppContext(): Context {
            return KoinApplication.context
        }

        fun getActivity(context: Context?): Activity? {
            if (context == null) {
                return null
            } else if (context is ContextWrapper) {
                return if (context is Activity) {
                    context
                } else {
                    getActivity(context.baseContext)
                }
            }
            return null
        }
    }

}