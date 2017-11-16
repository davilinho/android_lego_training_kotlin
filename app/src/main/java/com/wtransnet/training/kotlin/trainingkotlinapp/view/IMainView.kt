package com.wtransnet.training.kotlin.trainingkotlinapp.view

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem

/**
* Created by davidmartin on 23/10/17.
*/
interface IMainView: IView {
    fun showImages(list: List<LegoItem>, callback: () -> Unit)
    fun navigateToDetail(id: Int)
    fun deleteItemFeedback(item: LegoItem)
}