package com.wtransnet.training.kotlin.trainingkotlinapp.view

import android.content.Context

/**
* Created by davidmartin on 10/11/17.
*/
interface IView {
    val context: Context
    fun showLoading(callback: () -> Unit)
    fun hideLoading()
    fun showError(message: String)
    fun showOfflineMessage()
}