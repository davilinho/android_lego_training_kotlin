package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.MainUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.IMainView
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
* Created by davidmartin on 27/11/17.
*/
class MainPresenterTest {

    @Mock
    private val view = Mockito.mock(IMainView::class.java)

    @Mock
    private val useCase = Mockito.mock(MainUseCase::class.java)

    private val presenter = MainPresenter(view, useCase)

    @Test
    fun loadDetail() {
        presenter.loadDetail(1)
        verify(view, atLeastOnce()).navigateToDetail(1)
    }

    @Test
    fun deleteLegoItem() {
        val item = LegoItem(1, "", "", LegoItem.LegoType.ALL)
        presenter.deleteLegoItem(item)
        verify(view, atLeastOnce()).deleteItemFeedback(item)
    }
}