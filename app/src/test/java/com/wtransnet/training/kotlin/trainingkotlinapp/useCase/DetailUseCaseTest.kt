package com.wtransnet.training.kotlin.trainingkotlinapp.useCase

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.wtransnet.training.kotlin.trainingkotlinapp.datasource.DataSource
import com.wtransnet.training.kotlin.trainingkotlinapp.repository.Repository
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
* Created by davidmartin on 24/11/17.
*/
class DetailUseCaseTest {

    @Mock
    private val dataSource = Mockito.mock(DataSource()::class.java)

    @Mock
    private val repository = Mockito.mock(Repository::class.java)

    private val useCase = DetailUseCase(repository)

    @Test
    fun execute() {
        val item = DataSource().retrieveLego(1)

        whenever(dataSource.retrieveLego(1)).thenReturn(item)
        whenever(repository.retrieveDetail(1)).thenReturn(Observable.defer { Observable.just(Result(item!!)) })

        useCase.execute(1)
        verify(repository, atLeastOnce()).retrieveDetail(1)
    }
}