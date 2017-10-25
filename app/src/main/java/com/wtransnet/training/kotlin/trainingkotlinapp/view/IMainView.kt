package com.wtransnet.training.kotlin.trainingkotlinapp.view

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem

/**
* Created by davidmartin on 23/10/17.
*/
interface IMainView {
    fun showLoading()
    fun hideLoading()
    fun showImages(list: List<LegoItem>, callback: () -> Unit)
    fun navigateToDetail(id: Int)
}