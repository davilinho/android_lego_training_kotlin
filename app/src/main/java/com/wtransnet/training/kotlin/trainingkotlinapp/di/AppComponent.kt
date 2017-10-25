package com.wtransnet.training.kotlin.trainingkotlinapp.di

import com.wtransnet.training.kotlin.trainingkotlinapp.CustomApplication
import com.wtransnet.training.kotlin.trainingkotlinapp.modules.DetailComponent
import com.wtransnet.training.kotlin.trainingkotlinapp.modules.DetailModule
import com.wtransnet.training.kotlin.trainingkotlinapp.modules.MainComponent
import com.wtransnet.training.kotlin.trainingkotlinapp.modules.MainModule
import dagger.Component
import javax.inject.Singleton

/**
* Created by davidmartin on 23/10/17.
*/
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: CustomApplication)
    fun plus(homeModule: MainModule): MainComponent
    fun plus(detailModule: DetailModule): DetailComponent
}