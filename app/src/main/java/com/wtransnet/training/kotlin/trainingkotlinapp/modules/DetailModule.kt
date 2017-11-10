package com.wtransnet.training.kotlin.trainingkotlinapp.modules

import com.wtransnet.training.kotlin.trainingkotlinapp.datasource.DataSource
import com.wtransnet.training.kotlin.trainingkotlinapp.presenter.DetailPresenter
import com.wtransnet.training.kotlin.trainingkotlinapp.repository.Repository
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.DetailUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.DetailActivity
import dagger.Module
import dagger.Provides

/**
* Created by davidmartin on 24/10/17.
*/
@Module
class DetailModule(private val activity: DetailActivity) {
    @Provides
    fun providePresenter(useCase: DetailUseCase) = DetailPresenter(activity, useCase)

    @Provides
    fun provideUseCase(repository: Repository) = DetailUseCase(repository)

    @Provides
    fun provideRepository(dataSource: DataSource) = Repository(dataSource)

    @Provides
    fun provideDataSource() = DataSource()
}