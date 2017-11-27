package com.wtransnet.training.kotlin.trainingkotlinapp.repository

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.wtransnet.training.kotlin.trainingkotlinapp.datasource.DataSource
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
* Created by davidmartin on 23/11/17.
*/
class RepositoryTest {

    @Mock
    private val dataSource = Mockito.mock(DataSource::class.java)

    private val repository = Repository(dataSource)

    @Test
    fun retrieveListSuccess() {
        val successList = DataSource().retrieveLegoList()

        whenever(dataSource.retrieveLegoList()).thenReturn(successList)
        Assert.assertEquals(Result.ResultType.SUCCESS, repository.retrieveList().blockingFirst().type)

        repository.retrieveList()
        verify(dataSource, atLeastOnce()).retrieveLegoList()
    }

    @Test
    fun retrieveListFailure() {
        val failureList = emptyList<LegoItem>()

        whenever(dataSource.retrieveLegoList()).thenReturn(failureList)
        Assert.assertEquals(Result.ResultType.FAILURE, repository.retrieveList().blockingFirst().type)

        repository.retrieveList()
        verify(dataSource, atLeastOnce()).retrieveLegoList()
    }

    @Test
    fun retrieveDetailSuccess() {
        val legoId = 1
        val successDetail = DataSource().retrieveLego(legoId)

        whenever(dataSource.retrieveLego(legoId)).thenReturn(successDetail)
        Assert.assertEquals(Result.ResultType.SUCCESS, repository.retrieveDetail(legoId).blockingFirst().type)

        repository.retrieveDetail(legoId)
        verify(dataSource, atLeastOnce()).retrieveLego(legoId)
    }

    @Test
    fun retrieveDetailFailure() {
        val legoId = 1
        val failureDetail = null

        whenever(dataSource.retrieveLego(legoId)).thenReturn(failureDetail)
        Assert.assertEquals(Result.ResultType.FAILURE, repository.retrieveDetail(legoId).blockingFirst().type)

        repository.retrieveDetail(legoId)
        verify(dataSource, atLeastOnce()).retrieveLego(legoId)
    }
}
