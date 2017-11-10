package com.wtransnet.training.kotlin.trainingkotlinapp.view

import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem

/**
* Created by davidmartin on 24/10/17.
*/
interface IDetailView: IView {
    fun showDetail(item: LegoItem)
    fun showInfoDetail(message: String)
}