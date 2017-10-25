package com.wtransnet.training.kotlin.trainingkotlinapp.presenter

import com.wtransnet.training.kotlin.trainingkotlinapp.Repository
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.view.MainActivity

/**
* Created by davidmartin on 23/10/17.
*/
class MainPresenter(val view: MainActivity) {

    fun loadImages() {
        view.showLoading()
        Repository.retrieveList { response ->
            view.showImages(response) { view.hideLoading() }
        }
    }

    fun filterImages(byType: LegoItem.LegoType) {
        view.showLoading()
        Repository.retrieveList { response ->
            view.showImages(response.filter { it.type == byType } ) { view.hideLoading() }
        }
    }

    fun loadDetail(id: Int) {
        view.navigateToDetail(id)
    }
}