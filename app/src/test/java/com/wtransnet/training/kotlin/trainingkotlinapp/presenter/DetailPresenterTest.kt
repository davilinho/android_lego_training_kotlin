package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.DetailUseCase
import com.wtransnet.training.kotlin.trainingkotlinapp.view.IDetailView
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
* Created by davidmartin on 27/11/17.
*/
class DetailPresenterTest {

    @Mock
    private val view = Mockito.mock(IDetailView::class.java)

    @Mock
    private val useCase = Mockito.mock(DetailUseCase::class.java)

    private val presenter = DetailPresenter(view, useCase)

    @Test
    fun openInfoDetail() {
        val item = LegoItem(1, "", "", LegoItem.LegoType.ALL)
        presenter.openInfoDetail(item)
        with(item) {
            verify(view, atLeastOnce()).showInfoDetail("$id\n$text\n$url")
        }
    }
}