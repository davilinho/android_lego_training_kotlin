package com.wtransnet.training.kotlin.trainingkotlinapp.di

import com.wtransnet.training.kotlin.trainingkotlinapp.CustomApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
* Created by davidmartin on 23/10/17.
*/
@Module
class AppModule(val app: CustomApplication) {
    @Provides @Singleton
    fun provideApp() = app

//    @Provides @Singleton
//    fun provideMediaProvider(): Provider = MediaProvider
}