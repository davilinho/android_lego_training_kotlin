package com.wtransnet.training.kotlin.trainingkotlinapp.modules

import com.wtransnet.training.kotlin.trainingkotlinapp.view.MainActivity
import dagger.Subcomponent

/**
* Created by davidmartin on 23/10/17.
*/
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}