package com.wtransnet.training.kotlin.trainingkotlinapp.modules

import com.wtransnet.training.kotlin.trainingkotlinapp.datasource.DataSource
import com.wtransnet.training.kotlin.trainingkotlinapp.presenter.MainPresenter
import com.wtransnet.training.kotlin.trainingkotlinapp.repository.Repository
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.MainUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.MainActivity
import dagger.Module
import dagger.Provides

/**
* Created by davidmartin on 23/10/17.
*/
@Module
class MainModule(private val activity: MainActivity) {
    @Provides
    fun providePresenter(useCase: MainUseCase) = MainPresenter(activity, useCase)

    @Provides
    fun provideUseCase(repository: Repository) = MainUseCase(repository)

    @Provides
    fun provideRepository(dataSource: DataSource) = Repository(dataSource)

    @Provides
    fun provideDataSource() = DataSource()

}