package com.wtransnet.training.kotlin.trainingkotlinapp.modules

import com.wtransnet.training.kotlin.trainingkotlinapp.view.DetailActivity
import dagger.Subcomponent

/**
* Created by davidmartin on 24/10/17.
*/
@Subcomponent(modules = arrayOf(DetailModule::class))
interface DetailComponent {
    fun inject(activity: DetailActivity)
}