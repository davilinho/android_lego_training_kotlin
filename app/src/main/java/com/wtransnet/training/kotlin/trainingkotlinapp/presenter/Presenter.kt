package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.jobqueue.Executor
import com.wtransnet.training.kotlin.trainingkotlinapp.view.IView

/**
* Created by davidmartin on 14/11/17.
*/
open class Presenter(private val v: IView, priority: Executor.Priority = Executor.Priority.MID) {

    val executor: Executor = Executor(v.context, priority)

    protected fun showLoading(callback: () -> Unit) {
        v.showLoading { callback() }
    }

    protected fun hideLoading() {
        v.hideLoading()
    }

    protected fun showError(message: String) {
        v.showError(message)
    }

    protected fun showOfflineMessage() {
        v.showOfflineMessage()
    }
}