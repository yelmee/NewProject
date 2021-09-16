package com.example.newproejct

import android.app.Application
import android.content.Context

class MyApplication: Application() {

     companion object{
         lateinit var context: Context

         fun getAppContext(): Context {
             return MyApplication.context
         }
     }
    override fun onCreate() {
        super.onCreate()
        MyApplication.context = applicationContext
    }


}