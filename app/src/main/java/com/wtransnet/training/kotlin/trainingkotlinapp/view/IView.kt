package com.wtransnet.training.kotlin.trainingkotlinapp.view

/**
* Created by davidmartin on 10/11/17.
*/
interface IView {
    fun showLoading(callback: () -> Unit)
    fun hideLoading()
    fun showOfflineMessage()
}