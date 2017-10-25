package com.wtransnet.training.kotlin.trainingkotlinapp.modules

import com.wtransnet.training.kotlin.trainingkotlinapp.presenter.DetailPresenter
import com.wtransnet.training.kotlin.trainingkotlinapp.view.DetailActivity
import dagger.Module
import dagger.Provides

/**
* Created by davidmartin on 24/10/17.
*/
@Module
class DetailModule(private val activity: DetailActivity) {
    @Provides
    fun provideDetailPresenter() = DetailPresenter(activity)
}