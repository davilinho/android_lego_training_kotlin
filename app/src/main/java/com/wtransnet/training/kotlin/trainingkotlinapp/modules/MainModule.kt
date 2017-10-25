package com.wtransnet.training.kotlin.trainingkotlinapp.modules

import com.wtransnet.training.kotlin.trainingkotlinapp.presenter.MainPresenter
import com.wtransnet.training.kotlin.trainingkotlinapp.view.MainActivity
import dagger.Module
import dagger.Provides

/**
* Created by davidmartin on 23/10/17.
*/
@Module
class MainModule(private val activity: MainActivity) {
    @Provides
    fun provideMainPresenter() = MainPresenter(activity)
}