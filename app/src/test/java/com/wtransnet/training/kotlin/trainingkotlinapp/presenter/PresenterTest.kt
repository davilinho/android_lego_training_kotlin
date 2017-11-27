package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.wtransnet.training.kotlin.trainingkotlinapp.view.IView
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
* Created by davidmartin on 27/11/17.
*/
class PresenterTest {

    @Mock
    private var view = Mockito.mock(IView::class.java)

    private val presenter = Presenter(view)

    @Test
    fun hideLoading() {
        presenter.hideLoading()
        verify(view, atLeastOnce()).hideLoading()
    }

    @Test
    fun showError() {
        presenter.showError("Error")
        verify(view, atLeastOnce()).showError("Error")
    }

    @Test
    fun showOfflineMessage() {
        presenter.showOfflineMessage()
        verify(view, atLeastOnce()).showOfflineMessage()
    }
}