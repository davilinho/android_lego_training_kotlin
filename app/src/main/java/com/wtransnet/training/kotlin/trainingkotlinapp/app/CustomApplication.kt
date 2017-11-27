package com.wtransnet.training.kotlin.trainingkotlinapp.app

import android.app.Application
import com.wtransnet.training.kotlin.trainingkotlinapp.di.AppComponent
import com.wtransnet.training.kotlin.trainingkotlinapp.di.AppModule
import com.wtransnet.training.kotlin.trainingkotlinapp.di.DaggerAppComponent

/**
* Created by davidmartin on 23/10/17.
*/
class CustomApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}